package org.poison.order.core.req;

import lombok.Data;

@Data
public abstract class BaseRequest {

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名
     */
    private String operatorName;


}
