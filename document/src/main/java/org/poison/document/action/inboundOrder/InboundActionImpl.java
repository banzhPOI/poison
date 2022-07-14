package org.poison.document.action.inboundOrder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.poison.document.core.inboundOrder.InboundEvent;
import org.poison.document.core.inboundOrder.InboundOrderItemStatus;
import org.poison.statemachine.Action;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class InboundActionImpl implements InboundAction {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public Action<InboundOrderItemStatus, InboundEvent> init() {
        return ((from, to, event, ctx) -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            InboundOrderItemOperate o = (InboundOrderItemOperate) ctx;
            try {
                log.info("对象:{}", objectMapper.writeValueAsString(o));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            log.info("状态机action执行，从 {} 到 {}操作人：{} ,{}", from, to, o.getOperator().getOperatorId(), o.getOperator().getOperatorName());
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
