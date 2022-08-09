package org.poison.merge;

import lombok.extern.slf4j.Slf4j;

import org.poison.starter.utils.ShardingUtils;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 重写"任务名",取任务窗口数量","取任务线程池数量"三个方法
 * 原理：利用redis的RList取出任务时清理列表中全部相同任务
 */
@Slf4j
@Component
public abstract class ShardingMerge<T extends ShardingBaseTask> {

    private static final int DEFAULT_SHADING_NUM = 8;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 任务名
     */
    protected abstract String getTaskName();

    /**
     * 每次取任务的窗口数量，分片后就是每个分片都取这么多
     */
    protected abstract int getWindowNum();

    /**
     * 获取分片数量
     */
    protected int getShadingNum() {
        return DEFAULT_SHADING_NUM;
    }


    private Map<Integer, RList<T>> shardingTaskListMap = new HashMap<>();


    private RList<T> getRList(String shardingKey) {
        return shardingTaskListMap.get(ShardingUtils.getShardingNum(shardingKey, getShadingNum()));
    }

    /**
     * 初始化的时候要初始化所有分片，并且放到map里
     */
    @PostConstruct
    private void postConstruct() {
        List<String> shardingNameList = getShardingTaskNameList();
        for (int i = 0; i < shardingNameList.size(); i++) {
            shardingTaskListMap.put(i, redissonClient.getList(shardingNameList.get(i)));
        }
    }

    /**
     * 分片后的队列列表
     */
    private List<String> getShardingTaskNameList() {
        List<String> shardingList = new ArrayList<>();
        for (int i = 0; i < getShadingNum(); i++) {
            shardingList.add(getTaskName() + "_" + i);
        }
        return shardingList;
    }

    /**
     * 取任务线程池数量
     * 重写这个方法，调用get()来获取任务
     */
    protected abstract void handleTask();

    /**
     * 插入任务
     */
    public void add(T t) {
        RList<T> taskRList = getRList(t.getShardingKey());
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

    private List<T> getAndRemove() {
        List<T> list = new ArrayList<>();
        shardingTaskListMap.forEach((k, v) -> {
            list.addAll(getAndRemove(v));
        });
        return list;
    }

    /**
     * 获取并清理任务
     */
    private List<T> getAndRemove(RList<T> taskRList) {

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
