package org.poison.test.compactqueue;

import lombok.extern.slf4j.Slf4j;
import org.poison.compactqueue.Compaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Queue extends Compaction<Task> {
    @Override
    protected String getTaskName() {
        return "TEST";
    }

    @Override
    protected int getWindowNum() {
        return 10;
    }

    @Override
    public void handleTask() {
        List<Task> taskList = get();
        for (Task task : taskList) {
            log.info(task.getId());
        }
    }
}
