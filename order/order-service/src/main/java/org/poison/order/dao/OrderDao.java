package org.poison.order.dao;

import jakarta.annotation.Resource;
import org.poison.order.core.enums.BaseStatus;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.mapper.OrderMapper;
import org.poison.order.pojo.dto.OrderDTO;
import org.poison.order.pojo.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDao {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 创建订单
     */
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    /**
     * 根据主键查询
     */
    public OrderDTO findById(String id){
       return OrderDTO.fromEntity(orderMapper.findById(id));
    }


    /**
     * 根据主键更新状态
     */
    public void updateStatusById(String docId, BaseStatus status) {
        orderMapper.updateStatusById(docId, status);
    }

    /**
     * 根据主键更新失败原因
     */
    public void updateFailReason(String docId, String failReason) {
        orderMapper.updateFailReasonById(docId, failReason);
    }

    /**
     * 更新订单
     */
    public void updateOrder(OrderUpdateRequest req,OrderDTO orderDTO) {
        Order order = Order.fromUpdateRequest(req,orderDTO);
        orderMapper.updateOrder(order);
    }
}
