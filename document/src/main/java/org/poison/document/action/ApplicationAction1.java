package org.poison.document.action;

import lombok.extern.slf4j.Slf4j;
import org.poison.workflow.action.DefaultAction;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationAction1 extends DefaultAction<Object, Object, ApplicationAction1> {

    @Override
    public void doAction() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("do action1");
    }
}
