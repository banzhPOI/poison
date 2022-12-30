package org.poison.order.event.order;

import jakarta.annotation.Resource;
import org.poison.order.action.InfoAdmin;
import org.poison.order.action.RecordLog;
import org.poison.order.core.enums.BaseStatus;
import org.poison.order.core.enums.OrderStatus;
import org.poison.order.pojo.BasePojo.BaseAction;
import org.poison.order.pojo.BasePojo.BaseDocDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单取消事件
 */

@Component
public class OrderCancel extends BaseOrderEvent {

    @Resource
    private RecordLog recordLog;

    @Resource
    private InfoAdmin infoAdmin;

    /**
     * 需要重写这个方法
     * 事件触发前的状态列表（可接收状态列表）
     */
    @Override
    protected List<BaseStatus> getFromStatusList() {
        return List.of(OrderStatus.CREATE, OrderStatus.PASS, OrderStatus.REJECT);
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
        return List.of(recordLog, infoAdmin);
    }

}
