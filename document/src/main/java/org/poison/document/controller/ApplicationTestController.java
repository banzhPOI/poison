package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.action.ApplicationAction1;
import org.poison.document.transition.ApplicationFlow;
import org.poison.workflow.action.Action;
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
    public void build() {
        flow.fireEvent(1L, 1L);
    }
}
