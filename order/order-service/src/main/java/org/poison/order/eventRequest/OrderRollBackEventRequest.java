package org.poison.order.eventRequest;

import org.poison.order.core.req.OrderUpdateRequest;
import org.poison.order.pojo.BasePojo.BaseEventRequest;

public class OrderRollBackEventRequest extends BaseEventRequest {


    public static OrderRollBackEventRequest fromOperateRequest(OrderUpdateRequest updateRequest) {
        if (updateRequest == null) {
            return null;
        }
        OrderRollBackEventRequest eventRequest = new OrderRollBackEventRequest();
        eventRequest.setDocId(updateRequest.getId());
        eventRequest.setOperatorId(updateRequest.getOperatorId());
        eventRequest.setOperatorName(updateRequest.getOperatorName());
        return eventRequest;
    }
}
