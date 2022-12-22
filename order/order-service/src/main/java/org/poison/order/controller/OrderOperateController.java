package org.poison.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.order.client.OrderOperateClient;
import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderOperateController implements OrderOperateClient {

    /**
     * 订单审核通过
     */
    @Override
    public void pass(OrderOperateRequest operateRequest) {

    }

    /**
     * 订单审核拒绝
     */
    @Override
    public void reject(OrderOperateRequest operateRequest) {

    }

    /**
     * 订单变更
     */
    @Override
    public OrderDetailResponse update(OrderOperateRequest operateRequest) {
        return null;
    }

    /**
     * 订单取消
     */
    @Override
    public void cancel(OrderOperateRequest operateRequest) {

    }
}
