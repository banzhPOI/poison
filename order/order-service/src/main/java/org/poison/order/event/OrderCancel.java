package org.poison.order.event;

import lombok.Data;
import org.poison.order.core.enums.BaseStatus;
import org.poison.order.core.enums.OrderStatus;
import org.poison.order.pojo.BasePojo.BaseAction;
import org.poison.order.pojo.BasePojo.BaseDocDTO;

import java.util.List;

@Data
public class OrderCancel extends BaseEvent {


    /**
     * 需要重写这个方法
     * 单据前缀（加锁用）
     */
    @Override
    protected String getDocPrefix() {
        return "ORDER";
    }

    /**
     * 需要重写这个方法
     * 事件触发前的状态列表（可接收状态列表）
     */
    @Override
    protected List<BaseStatus> getFromStatusList() {
        return List.of(OrderStatus.CREATE,OrderStatus.PASS,OrderStatus.REJECT);
    }

    /**
     * 需要重写这个方法
     * 事件结束后的单据状态
     */
    @Override
    protected BaseStatus getToStatus() {
        return OrderStatus.CANCEL;
    }

    /**
     * 需要重写这个方法
     * 事件触发要执行的动作列表
     */
    @Override
    protected List<BaseAction> getActionList() {
        return null;
    }

    /**
     * 需要重写这个方法
     * 根据id获取单据
     *
     * @param docId
     */
    @Override
    protected BaseDocDTO getDocById(String docId) {
        return null;
    }

    /**
     * 需要重写这个方法
     * 更新单据目标状态
     *
     * @param docId
     * @param status
     */
    @Override
    protected void updateStatus(String docId, BaseStatus status) {

    }
}
