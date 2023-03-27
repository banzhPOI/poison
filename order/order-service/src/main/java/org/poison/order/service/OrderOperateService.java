package org.poison.order.service;

import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.core.resp.OrderDetailVO;

public interface OrderOperateService {


    /**
     * 订单审核通过
     */
    void pass(OrderOperateRequest operateRequest);

    /**
     * 订单审核拒绝
     */
    void reject(OrderOperateRequest operateRequest);

    /**
     * 订单取消
     */
    void cancel(OrderOperateRequest operateRequest);

    /**
     * 订单更新
     */
    OrderDetailVO update(OrderUpdateRequest updateRequest);
}
