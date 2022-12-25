package org.poison.order.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BizException;
import org.poison.order.client.OrderOperateClient;
import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.poison.order.service.OrderOperateService;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderOperateController implements OrderOperateClient {

    @Resource
    private OrderOperateService orderOperateService;

    /**
     * 订单审核通过
     */
    @Override
    public void pass(OrderOperateRequest operateRequest) {
        orderOperateService.pass(operateRequest);
        throw new BizException("test");
    }

    /**
     * 订单审核拒绝
     */
    @Override
    public void reject(OrderOperateRequest operateRequest) {
        orderOperateService.reject(operateRequest);

    }

    /**
     * 订单取消
     */
    @Override
    public void cancel(OrderOperateRequest operateRequest) {
        orderOperateService.cancel(operateRequest);
    }

    /**
     * 订单变更
     */
    @Override
    public OrderDetailResponse update(OrderUpdateRequest updateRequest) {
        return orderOperateService.update(updateRequest);
    }
}
