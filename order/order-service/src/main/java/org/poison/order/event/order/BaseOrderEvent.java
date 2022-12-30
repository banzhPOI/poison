package org.poison.order.event.order;

import jakarta.annotation.Resource;
import org.poison.order.core.enums.BaseStatus;
import org.poison.order.dao.OrderDao;
import org.poison.order.event.BaseEvent;
import org.poison.order.pojo.BasePojo.BaseAction;
import org.poison.order.pojo.BasePojo.BaseDocDTO;
import org.poison.order.pojo.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

public abstract class BaseOrderEvent extends BaseEvent {

    @Resource
    private OrderDao orderDao;

    public static final String DOC_PREFIX = "ORDER";

    /**
     * 需要重写这个方法
     * 单据前缀（加锁用）
     */
    @Override
    public String getDocPrefix() {
        return DOC_PREFIX;
    }

    /**
     * 需要重写这个方法
     * 根据id获取单据
     *
     * @param docId
     */
    @Override
    protected OrderDTO getDocById(String docId) {
        return orderDao.findById(docId);
    }

    /**
     * 需要重写这个方法
     * 更新单据目标状态
     */
    @Override
    protected void updateStatus(String docId, BaseStatus status) {
        orderDao.updateStatusById(docId, status);
    }

    /**
     * 需要重写这个方法
     * 在fireEvent失败的时候记录失败原因
     */
    @Override
    protected void updateFailReason(String docId, String failReason) {
        orderDao.updateFailReason(docId, failReason);
    }

    /**
     * 需要重写这个方法
     * 这里提供默认判断，给不需要判断的场景用
     */
    @Override
    protected void checkCondition(BaseDocDTO docDTO) {
    }

    /**
     * 需要重写这个方法
     * 事件触发要执行的动作列表
     * 这里提供默认动作，给不需要执行动作的场景用
     */
    @Override
    protected List<BaseAction> getActionList() {
        return Collections.emptyList();
    }
}
