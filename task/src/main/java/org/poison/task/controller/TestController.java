package org.poison.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.poison.task.distinct.Handler;
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
    private Handler handler;

    @PostMapping(value = "add")
    public void add() {
        for (int i = 0; i < 10; i++) {
            Task t = new Task();
            t.setId(Long.valueOf(i));
            t.setShardingKey("abc");
            t.setContent("abc" + 1);
            handler.add(t);
        }
        for (int i = 0; i < 10; i++) {
            Task t = new Task();
            t.setId(Long.valueOf(i));
            t.setShardingKey("abc");
            t.setContent("ABC" + 1);
            handler.add(t);
        }
    }
}
