package org.poison.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.poison.order.core.enums.BaseStatus;
import org.poison.order.pojo.entity.Order;

@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     */
    void addOrder(Order order);

    /**
     * 根据主键查询
     */
    Order findById(@Param("id") String id);

    /**
     * 根据主键更新状态
     */
    void updateStatusById(@Param("id") String id, @Param("status") BaseStatus status);

    /**
     * 根据主键更新失败原因
     */
    void updateFailReasonById(@Param("id") String id,@Param("failReason") String failReason);


    /**
     * 更新订单
     */
    void updateOrder(Order order);

}
