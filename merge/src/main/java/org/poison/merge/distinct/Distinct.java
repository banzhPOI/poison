package org.poison.merge.distinct;

import lombok.extern.slf4j.Slf4j;

import org.poison.merge.task.Task;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class Distinct {

    private final int window = 100;

    private RList<Task> taskRList;

    @Resource
    private RedissonClient redissonClient;

    @PostConstruct
    private void postConstruct() {
        taskRList = redissonClient.getList("abc");
    }

    public void add(Task task) {
        taskRList.add(task);
        log.info("add: list size is {}" ,taskRList.size());

    }

    public List<Task> getAndRemove() {
        List<Task> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(taskRList)) {
            return list;
        }
        if (taskRList.size() <= window) {
            list = Task.listClone(taskRList);
        } else {
            list = Task.listClone(taskRList.subList(0, window));
        }
        log.info("get: list size is {}" ,list.size());
        log.info("remove before: list size is {}" ,taskRList.size());
        taskRList.removeAll(list);
        log.info("remove after: list size is {}" ,taskRList.size());
        return list.stream().distinct().collect(Collectors.toList());
    }
}
