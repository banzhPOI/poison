package org.poison.order.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.page.PageResult;
import org.poison.order.client.OrderClient;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.poison.order.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController implements OrderClient {

    @Resource
    private OrderService orderService;
    /**
     * 订单创建
     */
    @Override
    public OrderDetailResponse create(OrderCreateRequest createRequest) {
        return orderService.create(createRequest);
    }

    /**
     * 订单检索
     */
    @Override
    public PageResult<OrderDetailResponse> search(OrderSearchRequest searchRequest) {
        return orderService.search(searchRequest);
    }

    /**
     * 订单变更
     */
    @Override
    public OrderDetailResponse update(OrderUpdateRequest updateRequest) {
        return orderService.update(updateRequest);
    }


}