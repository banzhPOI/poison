package org.poison.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.poison.task.distinct.Handle;
import org.poison.task.task.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private Handle handle;

    @PostMapping(value = "add")
    public void add() {
        for (int i = 0; i < 150; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                Task t = new Task();
                t.setId((long) finalI);
                t.setKey("abc" + finalI);
                try {
                    log.info("add task: {}", objectMapper.writeValueAsString(t));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                handle.add(t);
            });
            thread.start();
        }
    }

    @PostMapping(value = "get")
    public void get() {
        handle.handleTask();
    }
}
