package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.transition.ApplicationTransition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("applicationTransitions")
public class ApplicationTransitionController {

    @Resource
    private ApplicationTransition applicationTransition;

    @PostMapping(value = "build")
    public void build() {
        Long sourceStatusId = 1L;
        Long targetStatusId = 1L;
        Long eventId = 1L;
        Long actionId = 1L;
        ApplicationTransition transition = applicationTransition.build(sourceStatusId, targetStatusId, eventId, actionId);
        applicationTransition.add(transition);
    }
}
