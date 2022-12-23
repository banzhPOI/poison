package org.poison.order.pojo.BasePojo;

import lombok.Data;
import org.poison.order.core.enums.BaseStatus;
import org.poison.order.pojo.dto.OrderDTO;
import org.poison.order.pojo.entity.Order;

@Data
public abstract class BaseDocDTO {

    /**
     * 单据id
     */
    private String id;

    /**
     * 单据状态
     */
    private BaseStatus status;
}
