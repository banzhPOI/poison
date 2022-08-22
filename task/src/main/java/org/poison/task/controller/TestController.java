package org.poison.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.poison.task.distinct.Handle;
import org.poison.task.distinct.Handle2;
import org.poison.task.distinct.ShardingHandlerQueue;
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
    private ShardingHandlerQueue shardingHandler;

    @Resource
    private Handle handle;

    @Resource
    private Handle2 handle2;

    @PostMapping(value = "add")
    public void add() {
        for (int i = 0; i < 150; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                Task t = new Task();
                t.setId((long) finalI);
                t.setShardingKey("abc");
                try {
                    log.info("add task: {}", objectMapper.writeValueAsString(t));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                handle.add(t);
            });
            thread.start();
        }
        for (int i = 0; i < 150; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                Task t = new Task();
                t.setId((long) finalI);
                t.setShardingKey("abc");
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

    @PostMapping(value = "add2")
    public void add2() {
        for (int i = 0; i < 150; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                Task t = new Task();
                t.setId((long) finalI);
                t.setShardingKey("abc2" + finalI);
                try {
                    log.info("add task: {}", objectMapper.writeValueAsString(t));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                handle2.add(t);
            });
            thread.start();
        }
    }

    @PostMapping(value = "get")
    public void get() {
        handle.handleTask();
    }
}
