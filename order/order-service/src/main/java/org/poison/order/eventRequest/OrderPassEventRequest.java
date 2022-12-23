package org.poison.order.eventRequest;

import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.pojo.BasePojo.BaseEventRequest;

public class OrderPassEventRequest extends BaseEventRequest {


    public static OrderPassEventRequest fromOperateRequest(OrderOperateRequest operateRequest) {
        if (operateRequest == null) {
            return null;
        }
        OrderPassEventRequest eventRequest = new OrderPassEventRequest();
        eventRequest.setDocId(operateRequest.getId());
        eventRequest.setOperatorId(operateRequest.getOperatorId());
        eventRequest.setOperatorName(operateRequest.getOperatorName());
        return eventRequest;
    }
}
