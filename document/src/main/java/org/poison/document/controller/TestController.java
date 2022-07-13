package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.template.event.DocumentItemEventService;
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
    private DocumentItemEventService documentItemOperatorService;

    @PostMapping(value = "")
    public String test(@RequestBody Body abc) {
        documentItemOperatorService.init("",1L);
        return abc.a;
    }
}
