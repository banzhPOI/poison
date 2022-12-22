package org.poison.order.pojo.entity;

import lombok.Data;
import org.poison.order.core.enums.OrderStatus;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.starter.snowflake.IdUtils;

import java.time.Instant;

@Data
public class Order {

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

    /**
     * 创建时间
     */
    private Instant createTime;

    /**
     * 更新时间
     */
    private Instant updateTime;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名
     */
    private String operatorName;

    public static Order fromCreateRequest(OrderCreateRequest request) {
        if (request == null) {
            return null;
        }
        Order order = new Order();
        order.setId("O" + IdUtils.get());
        order.setOrderNo("ORDER" + IdUtils.get());
        order.setOperatorId(request.getOperatorId());
        order.setOperatorName(request.getOperatorName());
        return order;
    }
}