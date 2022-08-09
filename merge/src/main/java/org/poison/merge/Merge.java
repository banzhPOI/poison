package org.poison.merge;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 重写"任务名",取任务窗口数量","取任务线程池数量"三个方法
 * 原理：利用redis的RList取出任务时清理列表中全部相同任务
 */
@Slf4j
@Component
public abstract class Merge<T> {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 任务名
     */
    protected abstract String getTaskName();

    /**
     * 每次取任务的窗口数量
     */
    protected abstract int getWindowNum();

    /**
     * 取任务线程池数量
     * 重写这个方法，调用get()来获取任务
     */
    protected abstract void handleTask();

    private RList<T> taskRList;

    @PostConstruct
    private void postConstruct() {
        taskRList = redissonClient.getList(getTaskName());
    }

    /**
     * 插入任务
     */
    public void add(T t) {
        taskRList.add(t);
    }

    /**
     * 获取任务
     * 需要保证同一时间只有一个线程在运行
     */
    public List<T> get() {
        List<T> taskList = new ArrayList<>();
        RLock rLock = redissonClient.getLock(getTaskName() + "_LOCK");
        try {
            if (rLock.tryLock()) {
                try {
                    taskList = getAndRemove();
                } finally {
                    if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                        rLock.unlock();
                    }
                }
            }
        } catch (Exception e) {
            log.error("get task is abnormal", e);
        }
        return taskList;
    }

    /**
     * 获取并清理任务
     */
    private List<T> getAndRemove() {
        List<T> list = new ArrayList<>();
        List<T> needDeleteList;
        if (CollectionUtils.isEmpty(taskRList)) {
            return list;
        }
        if (taskRList.size() <= getWindowNum()) {
            needDeleteList = taskRList;
        } else {
            needDeleteList = taskRList.subList(0, getWindowNum());
        }
        list = needDeleteList;
        taskRList.removeAll(needDeleteList);
        return list.stream().distinct().collect(Collectors.toList());
    }

}
