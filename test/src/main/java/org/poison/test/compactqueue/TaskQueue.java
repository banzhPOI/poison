package org.poison.test.compactqueue;

import lombok.extern.slf4j.Slf4j;
import org.poison.compactqueue.ShardingCompaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TaskQueue extends ShardingCompaction<Task> {

    @Override
    protected String getTaskName() {
        return "TEST";
    }

    @Override
    protected long getDelay() {
        return 5000;
    }

    @Override
    public void handle(List<Task> taskList) {
        for (Task task : taskList) {
            log.info(task.getId());
        }
    }
}
