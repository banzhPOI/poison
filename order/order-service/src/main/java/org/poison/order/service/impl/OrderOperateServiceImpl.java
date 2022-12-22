package org.poison.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.poison.common.page.PageResult;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.poison.order.service.OrderOperateService;
import org.poison.order.service.OrderService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderOperateServiceImpl implements OrderOperateService {

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
     * 订单取消
     */
    @Override
    public void cancel(OrderOperateRequest operateRequest) {

    }
}
