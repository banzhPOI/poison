package org.poison.document.template.event;

import org.poison.statemachine.StateMachine;
import org.poison.statemachine.StateMachineFactory;
import lombok.extern.slf4j.Slf4j;
import org.poison.document.template.core.biz.DocumentItem;
import org.poison.document.template.core.ctx.Event;
import org.poison.document.template.core.ctx.Status;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventServiceImpl implements EventService {


    @Override
    public DocumentItem init(String userId, Long id) {
        StateMachine<Status, Event, DocumentItem> stateMachine = StateMachineFactory.get("DOCUMENT_ITEM_STATE_MACHINE");
        Status status =stateMachine.fireEvent(Status.CREATE, Event.INIT,new DocumentItem() );
        log.info(status.name());


        return null;
    }

    @Override
    public DocumentItem rollback(String userId, Long id) {
        return null;
    }

    @Override
    public DocumentItem execute(String userId, Long id) {
        StateMachine<Status, Event, DocumentItem> stateMachine = StateMachineFactory.get("DOCUMENT_ITEM_STATE_MACHINE");

        stateMachine.fireEvent(Status.PENDING, Event.EXECUTE,new DocumentItem() );

        return null;
    }

    @Override
    public DocumentItem cancel(String userId, Long id) {
        StateMachine<Status, Event, DocumentItem> stateMachine = StateMachineFactory.get("DOCUMENT_ITEM_STATE_MACHINE");

        stateMachine.fireEvent(Status.PENDING, Event.CANCEL,new DocumentItem() );

        return null;
    }


}
