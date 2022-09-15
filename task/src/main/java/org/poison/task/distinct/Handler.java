package org.poison.task.distinct;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.poison.merge.ShardingMerger;
import org.poison.task.task.Task;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.Resource;

@Slf4j
@Component
public class Handler extends ShardingMerger<Task> {

    @Resource
    private ObjectMapper objectMapper;

    private static final int INTERVAL_TIME = 1000;

    private static final int WINDOW_NUM = 100;

    private static final String TASK_NAME = "DEFAULT_TASK";

    @Override
    protected int getWindowNum() {
        return WINDOW_NUM;
    }

    @Override
    protected String getTaskName() {
        return TASK_NAME;
    }


    @Scheduled(fixedDelay = INTERVAL_TIME)
    @Override
    public void handleTask() {
        List<Task> taskList = get();
        for (Task task : taskList) {
            log.info(task.getId() + task.getContent());
        }
    }
}
