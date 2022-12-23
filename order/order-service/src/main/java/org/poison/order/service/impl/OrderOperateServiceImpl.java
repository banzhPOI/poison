package org.poison.order.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.order.components.DocLockComponent;
import org.poison.order.core.enums.OrderStatus;
import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.core.resp.OrderDetailResponse;
import org.poison.order.dao.OrderDao;
import org.poison.order.event.order.OrderCancel;
import org.poison.order.event.order.OrderRollBack;
import org.poison.order.eventRequest.OrderCancelEventRequest;
import org.poison.order.eventRequest.OrderRollBackEventRequest;
import org.poison.order.pojo.dto.OrderDTO;
import org.poison.order.service.OrderOperateService;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

/**
 * 设计思路
 * 单据的能力是由事件支撑的，所以这里提供的能力是各种事件的组合
 */
@Slf4j
@Service
public class OrderOperateServiceImpl implements OrderOperateService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private DocLockComponent lockComponent;

    @Resource
    private OrderCancel orderCancel;

    @Resource
    private OrderRollBack orderRollBack;

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
        orderCancel.fireEvent(OrderCancelEventRequest.fromOperateRequest(operateRequest));
    }

    /**
     * 订单更新
     * 有两种情况：
     * 一种是审核前,一种是审核后,审核前可以直接改,审核后需要回滚再改
     * 所以要先加锁判断
     */
    @Override
    public OrderDetailResponse update(OrderUpdateRequest req) {
        RLock lock = lockComponent.getOrderLock(req.getId());
        try {
            OrderDTO orderDTO = orderDao.findById(req.getId());
            //如果是审核前状态就直接更新,其他状态需要先回滚再更新
            if (orderDTO.getStatus()!= OrderStatus.CREATE){
                orderRollBack.fireEvent(OrderRollBackEventRequest.fromOperateRequest(req));
            }
            orderDao.updateOrder(req,orderDTO);
        } catch (Exception e) {
            log.error("lock doc: {} failed", req.getId(), e);
        } finally {
            //解锁
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return null;
    }
}
