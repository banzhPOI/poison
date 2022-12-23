package org.poison.order.pojo.BasePojo;

import lombok.Data;

@Data
public abstract class BaseEventRequest {

    /**
     * 单据id
     */
    private String docId;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名
     */
    private String operatorName;
}
