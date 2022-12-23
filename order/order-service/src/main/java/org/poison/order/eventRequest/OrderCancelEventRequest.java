package org.poison.order.eventRequest;

import org.poison.order.core.req.OrderOperateRequest;
import org.poison.order.pojo.BasePojo.BaseEventRequest;

public class OrderCancelEventRequest extends BaseEventRequest {


    public static OrderCancelEventRequest fromOperateRequest(OrderOperateRequest operateRequest) {
        if (operateRequest == null) {
            return null;
        }
        OrderCancelEventRequest eventRequest = new OrderCancelEventRequest();
        eventRequest.setDocId(operateRequest.getId());
        eventRequest.setOperatorId(operateRequest.getOperatorId());
        eventRequest.setOperatorName(operateRequest.getOperatorName());
        return eventRequest;
    }
}
