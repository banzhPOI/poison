package org.poison.document.action.inboundOrder;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.core.base.Operator;
import org.poison.document.core.inboundOrder.InboundEvent;
import org.poison.document.core.inboundOrder.InboundOrderItemStatus;
import org.poison.statemachine.Action;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InboundActionImpl implements InboundAction {

    @Override
    public Action<InboundOrderItemStatus, InboundEvent> init() {
        return ((from, to, event, ctx) -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Operator o = (Operator) ctx;
            log.info("状态机action执行，从 {} 到 {}操作人：{} ,{}", from, to, o.getOperatorId(), o.getOperatorName());
        });
    }

    @Override
    public Action<InboundOrderItemStatus, InboundEvent> execute() {
        return null;
    }

    @Override
    public Action<InboundOrderItemStatus, InboundEvent> cancel() {
        return null;
    }
}
