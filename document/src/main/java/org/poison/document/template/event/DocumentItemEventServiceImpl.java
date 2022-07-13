package org.poison.document.template.event;

import org.poison.statemachine.StateMachine;
import org.poison.statemachine.StateMachineFactory;
import lombok.extern.slf4j.Slf4j;
import org.poison.document.template.core.DocumentItem;
import org.poison.document.template.core.DocumentItemEvent;
import org.poison.document.template.core.DocumentItemStatus;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DocumentItemEventServiceImpl implements DocumentItemEventService {


    @Override
    public DocumentItem init(String userId, Long id) {
        StateMachine<DocumentItemStatus, DocumentItemEvent, DocumentItem> stateMachine = StateMachineFactory.get("DOCUMENT_ITEM_STATE_MACHINE");
        DocumentItemStatus status =stateMachine.fireEvent(DocumentItemStatus.CREATE,DocumentItemEvent.INIT,new DocumentItem() );
        log.info(status.name());


        return null;
    }

    @Override
    public DocumentItem rollback(String userId, Long id) {
        return null;
    }

    @Override
    public DocumentItem execute(String userId, Long id) {
        StateMachine<DocumentItemStatus, DocumentItemEvent, DocumentItem> stateMachine = StateMachineFactory.get("DOCUMENT_ITEM_STATE_MACHINE");

        stateMachine.fireEvent(DocumentItemStatus.PENDING,DocumentItemEvent.EXECUTE,new DocumentItem() );

        return null;
    }

    @Override
    public DocumentItem cancel(String userId, Long id) {
        StateMachine<DocumentItemStatus, DocumentItemEvent, DocumentItem> stateMachine = StateMachineFactory.get("DOCUMENT_ITEM_STATE_MACHINE");

        stateMachine.fireEvent(DocumentItemStatus.PENDING,DocumentItemEvent.CANCEL,new DocumentItem() );

        return null;
    }


}
