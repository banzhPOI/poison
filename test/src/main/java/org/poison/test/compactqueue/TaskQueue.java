package org.poison.test.compactqueue;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.compactqueue.ShardingCompaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TaskQueue extends ShardingCompaction<Task> {

    @Resource
    private TaskService service;

    @Override
    protected String getTaskName() {
        return "TEST";
    }

    @Override
    public void handle(List<Task> taskList) {
        for (Task task : taskList) {
            service.doTask(task);
        }
    }
}
