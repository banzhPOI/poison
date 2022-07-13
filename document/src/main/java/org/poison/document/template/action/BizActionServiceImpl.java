package org.poison.document.template.action;

import org.poison.statemachine.Action;
import lombok.extern.slf4j.Slf4j;
import org.poison.document.template.core.DocumentItem;
import org.poison.document.template.core.DocumentItemEvent;
import org.poison.document.template.core.DocumentItemStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BizActionServiceImpl implements BizActionService {

    @Override
    public Action<DocumentItemStatus, DocumentItemEvent, DocumentItem> preHandle() {
        return (from, to, event, ctx) -> {
            log.info("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx.getId());
            try {
                //休眠5秒，当做处理业务代码
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("");
        };
    }

    @Override
    public Action<DocumentItemStatus, DocumentItemEvent, DocumentItem> execute() {
        return (from, to, event, ctx) -> {
            log.info("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx.getId());
        };
    }

    @Override
    public Action<DocumentItemStatus, DocumentItemEvent, DocumentItem> cancel() {
        return (from, to, event, ctx) -> {
            log.info("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx.getId());
        };
    }
}
