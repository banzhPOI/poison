package org.poison.test.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.starter.snowflake.IdUtils;
import org.poison.starter.snowflake.IdWorker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("")
public class TestController {



    @PostMapping(value = "test")
    public void test() {
        log.info("hello world");
        for (int i=0;i<10;i++){
            log.info(String.valueOf(IdUtils.get()));
        }
    }

}
