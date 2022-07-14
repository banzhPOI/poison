package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BizException;
import org.poison.document.core.base.Operator;
import org.poison.document.core.inboundOrder.InboundEvent;
import org.poison.document.core.inboundOrder.InboundOrderItemStatus;
import org.poison.document.statemachine.InboundOrderItemStateMachine;
import org.poison.statemachine.StateMachine;
import org.poison.statemachine.StateMachineFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping(value = "")
    public String test(@RequestBody Operator o) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                StateMachine<InboundOrderItemStatus, InboundEvent> stateMachine = StateMachineFactory.get(InboundOrderItemStateMachine.MACHINE_ID);
                InboundOrderItemStatus target = stateMachine.fireEvent(InboundOrderItemStatus.CREATE, InboundEvent.INIT, o);
                if (target != InboundOrderItemStatus.PENDING) {
                    throw new BizException("wrongStatus " + target.name());
                }
            });
            thread.start();
        }
        return "hello world";
    }

}
