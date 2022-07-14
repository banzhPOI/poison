package org.poison.document.template.stateMachine;

import org.poison.document.template.core.ctx.Context;
import org.poison.statemachine.Condition;
import org.poison.statemachine.builder.StateMachineBuilder;
import org.poison.statemachine.builder.StateMachineBuilderFactory;
import org.poison.document.template.action.BizActionService;
import org.poison.document.template.core.ctx.Event;
import org.poison.document.template.core.ctx.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class StateMachine {

    @Resource
    private BizActionService bizActionService;

    private static final String MACHINE_ID = "DOCUMENT_ITEM_STATE_MACHINE";

    @Bean
    public void document() {
        StateMachineBuilder<Status, Event, Object> builder = StateMachineBuilderFactory.create();
        builder.internalTransition()
                .within(Status.CREATE)
                .on(Event.EDIT)
                .when(checkCondition())
                .perform(bizActionService.preHandle());
        builder.externalTransition()
                .from(Status.CREATE)
                .to(Status.PENDING)
                .on(Event.INIT)
                .when(checkCondition())
                .perform(bizActionService.preHandle());
        builder.externalTransition()
                .from(Status.PENDING)
                .to(Status.COMPLETE)
                .on(Event.EXECUTE)
                .when(checkCondition())
                .perform(bizActionService.execute());
        builder.externalTransition()
                .from(Status.PENDING)
                .to(Status.INVALID)
                .on(Event.CANCEL)
                .when(checkCondition())
                .perform(bizActionService.cancel());
        builder.build(MACHINE_ID);
    }

    private Condition<Object> checkCondition() {
        return (ctx) -> true;
    }
}
