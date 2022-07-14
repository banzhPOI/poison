package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.core.base.Operator;
import org.poison.document.core.inboundOrder.InboundEvent;
import org.poison.document.core.inboundOrder.InboundOrderItemStatus;
import org.poison.statemachine.Action;
import org.poison.statemachine.StateMachine;
import org.poison.statemachine.StateMachineFactory;
import org.poison.statemachine.builder.StateMachineBuilder;
import org.poison.statemachine.builder.StateMachineBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {


    @PostMapping(value = "")
    public String test(@RequestBody Body abc) {
        StateMachine<InboundOrderItemStatus, InboundEvent> stateMachine = StateMachineFactory.get("123");
        Operator o = new Operator();
        o.setOperatorId("11");
        o.setOperatorName("22");
        InboundOrderItemStatus target = stateMachine.fireEvent(InboundOrderItemStatus.CREATE, InboundEvent.INIT,o);
        return target.name();
    }

    @Bean
    private void init() {

        StateMachineBuilder<InboundOrderItemStatus, InboundEvent> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(InboundOrderItemStatus.CREATE)
                .to(InboundOrderItemStatus.PENDING)
                .on(InboundEvent.INIT)
                .perform(doAction());
        builder.build("123");
    }

    private Action<InboundOrderItemStatus, InboundEvent> doAction() {
        return (from, to, event, ctx) -> {
            Operator o = (Operator) ctx;
            System.out.println(o.getOperatorId() + " is operating  from:" + from + " to:" + to + " on:" + event);
        };
    }
}
