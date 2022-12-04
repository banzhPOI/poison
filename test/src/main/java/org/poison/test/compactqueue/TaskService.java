package org.poison.test.compactqueue;

import lombok.extern.slf4j.Slf4j;
import org.poison.compactqueue.ack.UseAck;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskService {

    @UseAck
    public void doTask(Task t){
        log.info(t.getId());
    }
}
