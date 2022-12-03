package org.poison.compactqueue;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.starter.web.utils.ShardingUtils;
import org.redisson.api.RQueue;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 可以重写默认分片数
 * 还要定义一个Sharding算法
 */
@Slf4j
@Component
public abstract class ShardingCompaction<T extends ShardingBaseTask> extends Compaction<T> {

    @Resource
    private RedissonClient redisson;

    /**
     * 默认分片数量为8
     */
    private static final int DEFAULT_SHADING_NUM = 8;

    /**
     * 获取分片数量
     */
    protected int getShadingNum() {
        return DEFAULT_SHADING_NUM;
    }

    private final Map<Integer, RQueue<T>> shardingQueueMap = new HashMap<>();

    private final Map<Integer, RSet<String>> shardingUniqueKeySetMap = new HashMap<>();


    /**
     * 根据shardingKey获取对应分片的队列
     */
    private RQueue<T> getQueue(String shardingKey) {
        return shardingQueueMap.get(ShardingUtils.getShardingNum(shardingKey, getShadingNum()));
    }

    /**
     * 根据shardingKey获取对应分片的set
     */
    private RSet<String> getUniqueKeySet(String shardingKey) {
        return shardingUniqueKeySetMap.get(ShardingUtils.getShardingNum(shardingKey, getShadingNum()));
    }

    /**
     * 初始化的时候要初始化所有分片，并且放到map里
     */
    @Override
    @PostConstruct
    protected void postConstruct() {
        List<String> shardingNameList = getShardingTaskNameList();
        for (int i = 0; i < shardingNameList.size(); i++) {
            shardingQueueMap.put(i, redisson.getQueue(shardingNameList.get(i) + "_QUEUE"));
            shardingUniqueKeySetMap.put(i, redisson.getSet(shardingNameList.get(i) + "_SET"));
        }
    }

    /**
     * 分片后的队列所有列表
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
        getUniqueKeySet(t.getShardingKey()).add(t.getUniqueKey());
        getQueue(t.getShardingKey()).add(t);
    }

    /**
     * 获取任务
     */
    @Override
    public List<T> get() {
        List<T> list = new ArrayList<>();
        shardingQueueMap.forEach((k, v) -> list.addAll(get(v, shardingUniqueKeySetMap.get(k))));
        return list;
    }

    protected List<T> get(RQueue<T> queue, RSet<String> uniqueKeySet) {
        //只取set中有的，取出来之后在Set中remove
        return queue.poll(getWindowNum()).stream().filter(t -> uniqueKeySet.remove(t.getUniqueKey())).collect(Collectors.toList());
    }
}
