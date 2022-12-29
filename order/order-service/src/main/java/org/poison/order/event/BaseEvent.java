package org.poison.order.event;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BizException;
import org.poison.order.core.enums.BaseStatus;
import org.poison.order.pojo.BasePojo.*;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class BaseEvent {

    /**
     * 需要重写这个方法
     * 单据前缀（加锁用）
     */
    protected abstract String getDocPrefix();

    /**
     * 需要重写这个方法
     * 事件触发前的状态列表（可接收状态列表）
     */
    protected abstract List<BaseStatus> getFromStatusList();

    /**
     * 需要重写这个方法
     * 事件结束后的单据状态
     */
    protected abstract BaseStatus getToStatus();

    /**
     * 需要重写这个方法
     * 条件判断throw异常中断流程
     */
    protected abstract void checkCondition(BaseDocDTO docDTO);

    /**
     * 需要重写这个方法
     * 事件触发要执行的动作列表
     */
    protected abstract List<BaseAction> getActionList();

    /**
     * 需要重写这个方法
     * 根据id获取单据
     */
    protected abstract BaseDocDTO getDocById(String docId);

    /**
     * 需要重写这个方法
     * 更新单据目标状态
     */
    protected abstract void updateStatus(String docId, BaseStatus status);

    /**
     * 需要重写这个方法
     * 在fireEvent失败的时候记录失败原因
     */
    protected abstract void updateFailReason(String docId, String failReason);

    /**
     * 单据状态校验
     */
    private void checkFromStatus(BaseStatus fromStatus) {
        if (!getFromStatusList().contains(fromStatus)) {
            throw new BizException("wrong fromStatus: " + fromStatus);
        }
    }


    /**
     * 事件触发
     * 加锁保证一张单据同一时间只能触发一个事件
     * 这里没有用@Transactional 因为状态是最后更新的，更新状态代表动作都执行成功了
     * 为什么没有直接返回成功/或者失败状态，这里发生失败简单重试并不能解决问题，应该按照失败原因具体分析处理
     * 0.加锁
     * 1.获取单据
     * 2.校验状态
     * 3.执行动作
     * 4.变更状态
     * 5.假如失败了，还应该记录一下原因
     */
    public void fireEvent(BaseEventRequest req) {
        try {
            lock = redisson.getLock(getDocPrefix() + req.getDocId());
            lock.tryLock(10, 5, TimeUnit.SECONDS);
            // 获取单据
            BaseDocDTO docDTO = getDocById(req.getDocId());
            if (docDTO == null) {
                throw new BizException("cant get doc: " + req.getDocId());
            }
            // 校验状态
            checkFromStatus(docDTO.getStatus());
            // 增加condition
            checkCondition(docDTO);
            // 执行动作
            getActionList().forEach(baseAction -> {
                // 区分执行有参动作和无参动作
                if (baseAction instanceof BaseNonParamAction nonParamAction) {
                    nonParamAction.doAction(docDTO);
                } else if (baseAction instanceof BaseParamAction paramAction) {
                    paramAction.doAction(docDTO, req);
                }
            });
            // 变更状态
            updateStatus(req.getDocId(), getToStatus());
        } catch (Exception e) {
            String failReason = "doc: " + req.getDocId() + " fire event: " + this.getClass().getSimpleName() + " failed, reason: " + e.getMessage();
            log.error("fire_event_failed: {}", failReason);
            updateFailReason(req.getDocId(), failReason);
        } finally {
            //解锁
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    private RLock lock;

    private RedissonClient redisson;

    @Resource
    private void setRedisson(RedissonClient redisson) {
        this.redisson = redisson;
    }

}
