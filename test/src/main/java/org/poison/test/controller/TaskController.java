package org.poison.test.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.test.compactqueue.Queue;
import org.poison.test.compactqueue.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("task")
public class TaskController {

    @Resource
    private Queue queue;

    @PostMapping(value = "test")
    public void test() {
        log.info("hello world");
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setId(String.valueOf(i));
            queue.add(task);
        }
        queue.handleTask();
    }

}
