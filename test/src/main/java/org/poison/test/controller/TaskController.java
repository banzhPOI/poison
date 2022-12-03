package org.poison.test.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.test.compactqueue.Task;
import org.poison.test.compactqueue.TaskQueue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("task")
public class TaskController {

    @Resource
    private TaskQueue taskQueue;

    @PostMapping(value = "test")
    public void test() {
        log.info("hello world");
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setId(String.valueOf(i));
            taskQueue.add(task);
        }
    }
}
