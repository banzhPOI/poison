package org.poison.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.common.page.PageResult;
import org.poison.order.client.OrderClient;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController implements OrderClient {

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
}
