package org.poison.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.event.order.OrderCancel;
import org.poison.order.eventRequest.OrderCancelEventRequest;
import org.poison.order.service.OrderOperateService;
import org.springframework.stereotype.Service;

/**
 * 设计思路
 * 单据的能力是由事件支撑的，所以这里提供的能力是各种事件的组合
 */
@Slf4j
@Service
public class OrderOperateServiceImpl implements OrderOperateService {

    /**
     * 订单审核通过
     */
    @Override
    public void pass(OrderOperateRequest operateRequest) {

    }

    /**
     * 订单审核拒绝
     */
    @Override
    public void reject(OrderOperateRequest operateRequest) {

    }

    /**
     * 订单取消
     */
    @Override
    public void cancel(OrderOperateRequest operateRequest) {
        OrderCancel orderCancel = new OrderCancel();
        orderCancel.fireEvent(OrderCancelEventRequest.fromOperateRequest(operateRequest));
    }
}
