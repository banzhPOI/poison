package org.poison.task.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class TestScheduled {

    @Async
    public void test(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("async");
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduled(){
        log.info("Scheduled");
    }
}
