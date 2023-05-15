package org.poison.order.client;

import org.poison.common.page.PageResult;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.resp.OrderDetailVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${spring.application.name}", contextId = "orders")
public interface OrderClient {

    /**
     * 订单创建
     */
    @PostMapping("orders/create")
    OrderDetailVO create(@RequestBody OrderCreateRequest createRequest);

    /**
     * 订单检索
     */
    @PostMapping("orders/search")
    PageResult<OrderDetailVO> search(@RequestBody OrderSearchRequest searchRequest);
}
