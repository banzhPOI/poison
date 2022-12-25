package org.poison.order.client;

import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banzh-order-service", contextId = "ordersOperate")
public interface OrderOperateClient {

    /**
     * 订单审核通过
     */
    @PostMapping("orders/operate/pass")
    void pass(@RequestBody OrderOperateRequest operateRequest);

    /**
     * 订单审核拒绝
     */
    @PostMapping("orders/operate/reject")
    void reject(@RequestBody OrderOperateRequest operateRequest);

    /**
     * 订单取消
     */
    @PostMapping("orders/operate/cancel")
    void cancel(@RequestBody OrderOperateRequest operateRequest);

    /**
     * 订单变更
     */
    @PostMapping("orders/operate/update")
    OrderDetailResponse update(@RequestBody OrderUpdateRequest updateRequest);
}
