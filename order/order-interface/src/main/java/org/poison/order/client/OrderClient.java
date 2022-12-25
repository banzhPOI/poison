package org.poison.order.client;

import org.poison.common.page.PageResult;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banzh-order-service", contextId = "orders")
public interface OrderClient {

    /**
     * 订单创建
     */
    @PostMapping("orders/create")
    OrderDetailResponse create(@RequestBody OrderCreateRequest createRequest);

    /**
     * 订单检索
     */
    @PostMapping("orders/search")
    PageResult<OrderDetailResponse> search(@RequestBody OrderSearchRequest searchRequest);
}
