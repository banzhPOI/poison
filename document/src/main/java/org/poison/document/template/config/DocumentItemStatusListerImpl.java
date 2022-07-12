package org.poison.document.template.config;

import org.poison.document.template.core.DocumentItem;
import org.poison.document.template.core.DocumentItemEvent;
import org.poison.document.template.core.DocumentItemStatus;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

@Component("documentItemStateListener")
@WithStateMachine(name = "documentItemStateMachine")
public class DocumentItemStatusListerImpl {

    @OnTransition(source = "CREATE", target = "PENDING")
    public boolean initTransition(Message<DocumentItemEvent> message) {
        DocumentItem item = (DocumentItem) message.getHeaders().get("item");
        item.setItemStatus(DocumentItemStatus.PENDING);
        System.out.println("预处理，状态机反馈信息：" + message.getHeaders());
        return true;
    }

    @OnTransition(source = "PENDING", target = "COMPLETE")
    public boolean executeTransition(Message<DocumentItemEvent> message) {
        DocumentItem item = (DocumentItem) message.getHeaders().get("item");
        item.setItemStatus(DocumentItemStatus.COMPLETE);
        System.out.println("执行，状态机反馈信息：" + message.getHeaders());
        return true;
    }

    @OnTransition(source = "PENDING", target = "INVALID")
    public boolean cancelTransition(Message<DocumentItemEvent> message) {
        DocumentItem item = (DocumentItem) message.getHeaders().get("item");
        item.setItemStatus(DocumentItemStatus.INVALID);
        System.out.println("作废，状态机反馈信息：" + message.getHeaders());
        return true;
    }
}
