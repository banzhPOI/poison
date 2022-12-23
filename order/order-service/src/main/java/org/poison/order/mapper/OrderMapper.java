package org.poison.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.poison.order.pojo.entity.Order;

@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     */
    void addOrder(Order order);
}
