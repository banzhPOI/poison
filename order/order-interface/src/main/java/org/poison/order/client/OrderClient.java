package org.poison.order.client;

import org.poison.common.page.PageResult;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service", contextId = "orders", url = "http://127.0.0.1:8081")
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
