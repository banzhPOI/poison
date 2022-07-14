package org.poison.document.statemachine;

import org.poison.document.action.inboundOrder.InboundAction;
import org.poison.document.core.inboundOrder.InboundEvent;
import org.poison.document.core.inboundOrder.InboundOrderItemStatus;
import org.poison.statemachine.builder.StateMachineBuilder;
import org.poison.statemachine.builder.StateMachineBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class InboundOrderItemStateMachine {

    public static final String MACHINE_ID = "INBOUND_ORDER_ITEM";

    @Resource
    private InboundAction action;

    @Bean
    private void stateMachine() {
        StateMachineBuilder<InboundOrderItemStatus, InboundEvent> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(InboundOrderItemStatus.CREATE)
                .to(InboundOrderItemStatus.PENDING)
                .on(InboundEvent.INIT)
                .perform(action.init());
        builder.build(MACHINE_ID);
    }
}
