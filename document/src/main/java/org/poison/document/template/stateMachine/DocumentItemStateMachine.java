package org.poison.document.template.stateMachine;

import org.poison.statemachine.Condition;
import org.poison.statemachine.builder.StateMachineBuilder;
import org.poison.statemachine.builder.StateMachineBuilderFactory;
import org.poison.document.template.action.BizActionService;
import org.poison.document.template.core.DocumentItem;
import org.poison.document.template.core.DocumentItemEvent;
import org.poison.document.template.core.DocumentItemStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class DocumentItemStateMachine {

    @Resource
    private BizActionService bizActionService;

    private static final String MACHINE_ID = "DOCUMENT_ITEM_STATE_MACHINE";

    @Bean
    public void document() {
        StateMachineBuilder<DocumentItemStatus, DocumentItemEvent, DocumentItem> builder = StateMachineBuilderFactory.create();
        builder.internalTransition()
                .within(DocumentItemStatus.CREATE)
                .on(DocumentItemEvent.EDIT)
                .when(checkCondition())
                .perform(bizActionService.preHandle());
        builder.externalTransition()
                .from(DocumentItemStatus.CREATE)
                .to(DocumentItemStatus.PENDING)
                .on(DocumentItemEvent.INIT)
                .when(checkCondition())
                .perform(bizActionService.preHandle());
        builder.externalTransition()
                .from(DocumentItemStatus.PENDING)
                .to(DocumentItemStatus.COMPLETE)
                .on(DocumentItemEvent.EXECUTE)
                .when(checkCondition())
                .perform(bizActionService.execute());
        builder.externalTransition()
                .from(DocumentItemStatus.PENDING)
                .to(DocumentItemStatus.INVALID)
                .on(DocumentItemEvent.CANCEL)
                .when(checkCondition())
                .perform(bizActionService.cancel());
        builder.build(MACHINE_ID);
    }

    private Condition<DocumentItem> checkCondition() {
        return (ctx) -> true;
    }
}
