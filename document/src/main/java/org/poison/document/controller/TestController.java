package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BizException;
import org.poison.document.action.inboundOrder.InboundOrderItemOperate;
import org.poison.document.core.base.Operator;
import org.poison.document.core.inboundOrder.InboundEvent;
import org.poison.document.core.inboundOrder.InboundOrderItem;
import org.poison.document.core.inboundOrder.InboundOrderItemStatus;
import org.poison.document.dto.OperateDTO;
import org.poison.document.statemachine.InboundOrderItemStateMachine;
import org.poison.statemachine.StateMachine;
import org.poison.statemachine.StateMachineFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("inboundOrders")
public class TestController {

    @PostMapping(value = "init/item")
    public String test(@RequestBody OperateDTO o) {

        InboundOrderItem item = new InboundOrderItem();
        item.setShelfInventoryId("123");
        InboundOrderItemOperate itemOperate = new InboundOrderItemOperate();
        itemOperate.setUserId(o.getUserId());
        itemOperate.setShelfInventoryId(item.getShelfInventoryId());
        itemOperate.setQuantity(12L);
        itemOperate.setOperator(new Operator() {
        });
        StateMachine<InboundOrderItemStatus, InboundEvent> stateMachine = StateMachineFactory.get(InboundOrderItemStateMachine.MACHINE_ID);
        InboundOrderItemStatus target = stateMachine.fireEvent(InboundOrderItemStatus.CREATE, InboundEvent.INIT, itemOperate);
        if (target != InboundOrderItemStatus.PENDING) {
            throw new BizException("wrongStatus " + target.name());
        }
        return "hello world";
    }
}
