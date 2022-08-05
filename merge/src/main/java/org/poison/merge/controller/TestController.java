package org.poison.merge.controller;

import lombok.extern.slf4j.Slf4j;

import org.poison.merge.distinct.Distinct;
import org.poison.merge.task.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private Distinct distinct;

    @PostMapping(value = "")
    public List<Task> test() {
        Task t = new Task();
        t.setId(1L);
        t.setName("abc");
        for (int i = 0; i < 150; i++) {
            distinct.add(t);
        }
        return distinct.getAndRemove();
    }
}
