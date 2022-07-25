package org.poison.document.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.document.status.ApplicationStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("applicationStatuses")
public class ApplicationStatusController {

    @Resource
    private ApplicationStatus applicationStatus;

    @PostMapping(value = "add")
    public void add(@RequestBody ApplicationStatus e) {
        applicationStatus.add(e);
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam Long id) {
        applicationStatus.deleteById(id);
    }

    @PutMapping(value = "update")
    public void update(@RequestBody ApplicationStatus e) {
        applicationStatus.update(e);
    }

    @GetMapping(value = "list")
    public List<ApplicationStatus> list() {
        return applicationStatus.getList();
    }
}
