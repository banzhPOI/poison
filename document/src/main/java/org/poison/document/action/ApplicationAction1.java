package org.poison.document.action;

import lombok.extern.slf4j.Slf4j;
import org.poison.workflow.action.DefaultAction;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationAction1 extends DefaultAction<String, String, ApplicationAction1> {

    @Override
    public void doAction() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("do action1");
    }

    @Override
    public String doActionWithResult(String param) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String result = "result" + param;
        log.info("do action1 with param: {} and result: {}", param, result);
        return result;
    }
}
