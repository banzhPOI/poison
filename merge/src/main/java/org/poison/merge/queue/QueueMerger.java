package org.poison.merge.queue;

import lombok.extern.slf4j.Slf4j;

import org.poison.merge.BaseTask;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

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
public abstract class QueueMerger<T extends BaseTask>{

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

    private RQueue<T> taskQueue;

    @PostConstruct
    protected void postConstruct() {
        taskQueue = redissonClient.getQueue(getTaskName());
    }

    /**
     * 插入任务
     */
    public void add(T t) {
        taskQueue.add(t);
    }

    /**
     * 获取任务
     * 并且删除队列中一样的元素
     */
    public List<T> get() {
        List<T> list = taskQueue.poll(getWindowNum());
        taskQueue.removeAll(list);
        return list.stream().distinct().collect(Collectors.toList());
    }
}
