package org.poison.order.client;

import org.poison.common.page.PageResult;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("orders")
@FeignClient(name = "order-service", contextId = "orders")
public interface OrderClient {

    /**
     * 订单创建
     */
    @PostMapping("create")
    OrderDetailResponse create(OrderCreateRequest createRequest);

    /**
     * 订单检索
     */
    @PostMapping("search")
    PageResult<OrderDetailResponse> search(OrderSearchRequest searchRequest);

    /**
     * 订单变更
     */
    @PostMapping("update")
    OrderDetailResponse update(OrderUpdateRequest updateRequest);

}