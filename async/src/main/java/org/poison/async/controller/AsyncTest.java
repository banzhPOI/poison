package org.poison.async.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncTest {

    @Scheduled(fixedDelay = 1000)
    public void scheduled1(){
        log.info("Scheduled-1");
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduled2(){
        log.info("Scheduled-2");
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduled3(){
        log.info("Scheduled-3");
    }

    @Async
    public void test(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("async");
    }
}
