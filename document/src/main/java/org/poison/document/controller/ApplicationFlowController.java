package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.transition.ApplicationFlow;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("applicationFlows")
public class ApplicationFlowController {

    @Resource
    private ApplicationFlow applicationFlow;

    @PostMapping(value = "build")
    public void build() {
        Long sourceStatusId = 1L;
        Long targetStatusId = 1L;
        Long eventId = 1L;
        Long actionId = 1L;
        ApplicationFlow transition = applicationFlow.build(sourceStatusId, targetStatusId, eventId, actionId);
        applicationFlow.add(transition);
    }
}
