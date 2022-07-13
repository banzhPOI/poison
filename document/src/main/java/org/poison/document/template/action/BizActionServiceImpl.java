package org.poison.document.template.action;

import org.poison.document.template.core.ctx.Context;
import org.poison.statemachine.Action;
import lombok.extern.slf4j.Slf4j;
import org.poison.document.template.core.ctx.Event;
import org.poison.document.template.core.ctx.Status;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BizActionServiceImpl implements BizActionService {

    @Override
    public Action<Status, Event, Context> preHandle() {
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
    public Action<Status, Event, Context> execute() {
        return (from, to, event, ctx) -> {
            log.info("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx.getId());
        };
    }

    @Override
    public Action<Status, Event, Context> cancel() {
        return (from, to, event, ctx) -> {
            log.info("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx.getId());
        };
    }
}
