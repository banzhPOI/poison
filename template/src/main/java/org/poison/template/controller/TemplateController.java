package org.poison.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("")
public class TemplateController {

    @PostMapping(value = "test")
    public void test() {
        log.info("hello world");
    }

}
