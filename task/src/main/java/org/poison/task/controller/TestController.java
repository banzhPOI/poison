package org.poison.task.controller;

import lombok.extern.slf4j.Slf4j;

import org.poison.merge.BaseTask;
import org.poison.merge.Merge;
import org.poison.task.task.Task;
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
    private Merge merge;

    @PostMapping(value = "")
    public List<BaseTask> test() {
        BaseTask t = new Task();
        t.setId(1L);
        t.setKey("abc");
        for (int i = 0; i < 150; i++) {
            merge.add(t);
        }
        return merge.getAndRemove();
    }
}
