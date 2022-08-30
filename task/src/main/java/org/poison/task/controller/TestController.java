package org.poison.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.poison.merge.ack.UseAck;
import org.poison.task.distinct.SetHandler;
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
    private SetHandler setHandler;

    @PostMapping(value = "add")
    public void add() {
        for (int i =0 ;i<10;i++){
            Task t = new Task();
            t.setId(Long.valueOf(i));
            t.setContent("abc"+1);
            setHandler.add(t);
        }
        for (int i =0 ;i<10;i++){
            Task t = new Task();
            t.setId(Long.valueOf(i));
            t.setContent("ABC"+1);
            setHandler.add(t);
        }
    }

    @PostMapping(value = "get")
    public void get() {
        setHandler.handleTask();
    }
}
