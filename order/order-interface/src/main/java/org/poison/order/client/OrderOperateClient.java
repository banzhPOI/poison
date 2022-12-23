package org.poison.order.client;

import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("orders/operate")
@FeignClient(name = "order-service", contextId = "ordersOperate")
public interface OrderOperateClient {

    /**
     * 订单审核通过
     */
    @PostMapping("pass")
    void pass(@RequestBody OrderOperateRequest operateRequest);

    /**
     * 订单审核拒绝
     */
    @PostMapping("reject")
    void reject(@RequestBody OrderOperateRequest operateRequest);

    /**
     * 订单取消
     */
    @PostMapping("cancel")
    void cancel(@RequestBody OrderOperateRequest operateRequest);
}
