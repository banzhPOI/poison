package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.event.ApplicationEvent;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("applicationEvents")
public class ApplicationEventController {

    @Resource
    private ApplicationEvent applicationEvent;

    @PostMapping(value = "add")
    public void add(@RequestBody ApplicationEvent e) {
        applicationEvent.add(e);
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam Long id) {
        applicationEvent.deleteById(id);
    }

    @PutMapping(value = "update")
    public void update(@RequestBody ApplicationEvent e) {
        applicationEvent.update(e);
    }

    @GetMapping(value = "list")
    public List<ApplicationEvent> list() {
        return applicationEvent.getList();
    }
}
