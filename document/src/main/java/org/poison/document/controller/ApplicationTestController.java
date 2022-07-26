package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.flow.ApplicationFlow;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class ApplicationTestController {

    @Resource
    private ApplicationFlow flow;

    @PostMapping(value = "")
    public void test() {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                String result = (String) flow.fireEventWithResult(1L, 1L, "paramAbc");
            });
            thread.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                String result = (String) flow.fireEventWithResult(1L, 2L, "paramAbc2");
            });
            thread.start();
        }
    }
}
