package org.poison.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.poison.common.page.PageResult;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.poison.order.service.OrderService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 订单创建
     */
    @Override
    public OrderDetailResponse create(OrderCreateRequest createRequest) {
        return null;
    }

    /**
     * 订单检索
     */
    @Override
    public PageResult<OrderDetailResponse> search(OrderSearchRequest searchRequest) {
        return null;
    }

    /**
     * 订单更新
     */
    @Override
    public OrderDetailResponse update(OrderUpdateRequest updateRequest) {
        return null;
    }
}
