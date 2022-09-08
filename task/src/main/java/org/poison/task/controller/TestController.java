package org.poison.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.poison.merge.ack.UseAck;
import org.poison.task.distinct.SetHandler;
import org.poison.task.task.Task;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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


    @Resource
    @Qualifier("testThreadPool")
    private ExecutorService testExecutor;
    
    @PostMapping(value = "get")
    public void get() {
        List<String> list = new ArrayList<>();
        for (int i =0 ;i<100;i++){
            list.add("abc"+i);
        }
        
        List<Future<?>> futureList = new ArrayList<>();
        for (String s : list) {
            Future<?> future = testExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info(s);
            });
            futureList.add(future);
        }
        futureList.forEach(it -> {
            try {
                it.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        log.info("finish");
        
    }

    @Resource
    private TestScheduled testScheduled;

    @PostMapping(value = "test")
    public void test() {
        for (int i =0;i<10000;i++){
            testScheduled.test();
        }
    }
}
