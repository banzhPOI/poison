package org.poison.order.service;

import org.poison.common.page.PageResult;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.resp.OrderDetailResponse;

public interface OrderService {

    /**
     * 订单创建
     */
    OrderDetailResponse create(OrderCreateRequest createRequest);

    /**
     * 订单检索
     */
    PageResult<OrderDetailResponse> search(OrderSearchRequest searchRequest);

}
