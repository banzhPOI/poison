package org.poison.order.event.order;

import org.poison.order.core.enums.BaseStatus;
import org.poison.order.core.enums.OrderStatus;
import org.poison.order.pojo.BasePojo.BaseAction;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 订单回滚事件
 */
@Component
public class OrderRollBack extends BaseOrderEvent {

    /**
     * 需要重写这个方法
     * 事件触发前的状态列表（可接收状态列表）
     */
    @Override
    protected List<BaseStatus> getFromStatusList() {
        return List.of(OrderStatus.PASS, OrderStatus.REJECT);
    }

    /**
     * 需要重写这个方法
     * 事件结束后的单据状态
     */
    @Override
    protected BaseStatus getToStatus() {
        return OrderStatus.CREATE;
    }

}
