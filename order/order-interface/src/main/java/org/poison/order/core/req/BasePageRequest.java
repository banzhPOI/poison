package org.poison.order.core.req;

import lombok.Data;
import org.poison.common.page.PageRequest;

@Data
public abstract class BasePageRequest extends PageRequest {

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名
     */
    private String operatorName;


}
