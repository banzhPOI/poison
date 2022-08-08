package org.poison.merge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.poison.starter.utils.CloneList;
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
public abstract class Merge {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 每次取出的窗口数量
     */
    protected abstract int getWindowNum();

    /**
     * 任务名
     */
    protected abstract String getTaskName();


    private RList<BaseTask> taskRList;

    @PostConstruct
    private void postConstruct() {
        taskRList = redissonClient.getList(getTaskName());
    }

    public void add(BaseTask baseTask) {
        taskRList.add(baseTask);
    }

    public List<BaseTask> getAndRemove() {
        List<BaseTask> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(taskRList)) {
            return list;
        }
        if (taskRList.size() <= getWindowNum()) {
            list = CloneList.cloneTaskList(taskRList);
        } else {
            list = CloneList.cloneTaskList(taskRList.subList(0, getWindowNum()));
        }
        taskRList.removeAll(list);
        return list.stream().distinct().collect(Collectors.toList());
    }

}
