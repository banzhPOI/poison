package org.poison.order.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.page.PageResult;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderSearchRequest;
import org.poison.order.core.resp.OrderDetailVO;
import org.poison.order.dao.OrderDao;
import org.poison.order.pojo.entity.Order;
import org.poison.order.service.OrderService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    /**
     * 订单创建
     */
    @Override
    public OrderDetailVO create(OrderCreateRequest createRequest) {
        Order order = Order.fromCreateRequest(createRequest);
        orderDao.addOrder(order);
        return null;
    }

    /**
     * 订单检索
     */
    @Override
    public PageResult<OrderDetailVO> search(OrderSearchRequest searchRequest) {
        return null;
    }


}
