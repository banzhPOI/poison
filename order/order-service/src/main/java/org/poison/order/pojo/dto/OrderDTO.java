package org.poison.order.pojo.dto;

import lombok.Data;
import org.poison.order.core.enums.OrderStatus;
import org.poison.order.pojo.BasePojo.BaseDocDTO;
import org.poison.order.pojo.entity.Order;

import java.time.Instant;

@Data
public class OrderDTO extends BaseDocDTO {

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

    /**
     * 失败原因
     */
    private String failReason;

    public static OrderDTO fromEntity(Order entity){
        if (entity==null){
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setOrderNo(entity.getOrderNo());
        dto.setStatus(entity.getStatus());
        dto.setCreateTime(entity.getCreateTime());
        dto.setUpdateTime(entity.getCreateTime());
        dto.setOperatorId(entity.getOperatorId());
        dto.setOperatorName(entity.getOperatorName());
        dto.setFailReason(entity.getFailReason());
        return dto;
    }
}
