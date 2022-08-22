package org.poison.merge.queue;

import lombok.extern.slf4j.Slf4j;

import org.poison.merge.ShardingBaseTask;
import org.poison.starter.utils.ShardingUtils;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

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
public abstract class ShardingQueueMerger<T extends ShardingBaseTask> extends QueueMerger<T> {

    private static final int DEFAULT_SHADING_NUM = 8;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取分片数量
     */
    protected int getShadingNum() {
        return DEFAULT_SHADING_NUM;
    }

    private final Map<Integer, RQueue<T>> shardingTaskQueueMap = new HashMap<>();


    private RQueue<T> getQueue(String shardingKey) {
        return shardingTaskQueueMap.get(ShardingUtils.getShardingNum(shardingKey, getShadingNum()));
    }

    /**
     * 初始化的时候要初始化所有分片，并且放到map里
     */
    @Override
    @PostConstruct
    protected void postConstruct() {
        List<String> shardingNameList = getShardingTaskNameList();
        for (int i = 0; i < shardingNameList.size(); i++) {
            shardingTaskQueueMap.put(i, redissonClient.getQueue(shardingNameList.get(i)));
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
     * 插入任务
     */
    @Override
    public void add(T t) {
        getQueue(t.getShardingKey()).add(t);
    }

    /**
     * 获取任务
     */
    @Override
    public List<T> get() {
        List<T> list = new ArrayList<>();
        shardingTaskQueueMap.forEach((k, v) -> list.addAll(get(v)));
        return list;
    }

    private List<T> get(RQueue<T> taskQueue) {
        List<T> list = taskQueue.poll(getWindowNum());
        taskQueue.removeAll(list);
        return list.stream().distinct().collect(Collectors.toList());
    }
}