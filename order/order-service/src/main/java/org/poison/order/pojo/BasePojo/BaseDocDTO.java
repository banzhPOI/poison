package org.poison.order.pojo.BasePojo;

import lombok.Data;
import org.poison.order.core.enums.BaseStatus;

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
