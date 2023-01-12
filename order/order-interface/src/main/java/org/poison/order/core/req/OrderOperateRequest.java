package org.poison.order.core.req;

import lombok.Data;

@Data
public class OrderOperateRequest extends BaseRequest{

    /**
     * 订单id
     */
    private String id;
}
