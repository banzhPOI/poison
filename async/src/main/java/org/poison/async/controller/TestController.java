package org.poison.async.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private AsyncTest asyncTest;

    @PostMapping(value = "")
    public String test() {
        for (int i= 0;i<1000;i++){
            asyncTest.test();
        }
        return "helloWorld";
    }
}
