package org.poison.test.compactqueue;

import lombok.extern.slf4j.Slf4j;
import org.poison.compactqueue.Compaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TaskQueue extends Compaction<Task> {

    @Override
    protected String getTaskName() {
        return "TEST";
    }

    @Override
    public void handle(List<Task> taskList) {
        for (Task task : taskList) {
            log.info(task.getId());
        }
    }
}
