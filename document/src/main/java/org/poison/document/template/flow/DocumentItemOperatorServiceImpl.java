package org.poison.document.template.flow;

import org.poison.document.template.core.DocumentItem;
import org.poison.document.template.core.DocumentItemEvent;
import org.poison.document.template.core.DocumentItemStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DocumentItemOperatorServiceImpl implements DocumentItemOperatorService {

    @Resource
    private StateMachine<DocumentItemStatus, DocumentItemEvent> documentItemStateMachine;

    @Resource
    private StateMachinePersister<DocumentItemStatus, DocumentItemEvent, DocumentItem> persister;

    @Override
    public DocumentItem init(String userId, Long id) {
        DocumentItem item = null;
        Message message = MessageBuilder.withPayload(DocumentItemEvent.INIT).setHeader("documentItem", item).build();
        if (!sendEvent(message, item)) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 支付失败, 状态异常，订单号：" + id);
        }
        return null;
    }

    @Override
    public DocumentItem execute(String userId, Long id) {
        return null;
    }

    @Override
    public DocumentItem cancel(String userId, Long id) {
        return null;
    }

    /**
     * 发送单项状态转换事件
     */
    private synchronized boolean sendEvent(Message<DocumentItemEvent> message, DocumentItem item) {
        boolean result = false;
        try {
            documentItemStateMachine.start();
            //尝试恢复状态机状态
            persister.restore(documentItemStateMachine, item);
            //添加延迟用于线程安全测试
            Thread.sleep(1000);
            result = documentItemStateMachine.sendEvent(message);
            //持久化状态机状态
            persister.persist(documentItemStateMachine, item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documentItemStateMachine.stop();
        }
        return result;
    }
}
