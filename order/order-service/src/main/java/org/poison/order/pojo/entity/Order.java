package org.poison.order.pojo.entity;

import lombok.Data;
import org.poison.order.core.enums.OrderStatus;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.pojo.BasePojo.BaseDocEntity;
import org.poison.order.pojo.dto.OrderDTO;
import org.poison.starter.snowflake.IdUtils;

import java.time.Instant;

@Data
public class Order extends BaseDocEntity {

    /**
     * 订单id
     */
    private String id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单状态
     */
    private OrderStatus status;


    public static Order fromCreateRequest(OrderCreateRequest request) {
        if (request == null) {
            return null;
        }
        Order order = new Order();
        order.setId("O" + IdUtils.get());
        order.setOrderNo("ORDER" + IdUtils.get());
        order.setStatus(OrderStatus.CREATE);
        order.setOperatorId(request.getOperatorId());
        order.setOperatorName(request.getOperatorName());
        return order;
    }

    public static Order fromUpdateRequest(OrderUpdateRequest request, OrderDTO orderDTO) {
        if (request == null) {
            return null;
        }
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderNo(orderDTO.getOrderNo());
        order.setOperatorId(request.getOperatorId());
        order.setOperatorName(request.getOperatorName());
        return order;
    }
}
