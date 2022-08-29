package org.poison.merge.set;

import org.redisson.api.RQueue;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public abstract class SetMerger<T extends BaseTask>{

    @Resource
    private RedissonClient redisson;

    /**
     * 任务名
     */
    protected abstract String getTaskName();

    /**
     * 每次取任务的窗口数量
     */
    protected abstract int getWindowNum();

    /**
     * 重写这个方法，调用get()来获取任务
     */
    protected abstract void handleTask();

    private RQueue<T> queue;

    private RSet<String> uniqueKeySet;

    @PostConstruct
    private void postConstruct() {
        queue = redisson.getQueue(getQueueName());
        uniqueKeySet = redisson.getSet(getSetName());
    }

    public void add(T t) {
        queue.add(t);
        uniqueKeySet.add(t.getUniqueKey());
    }

    /**
     * lpop不会有并发问题
     */
    public List<T> get() {
        List<T> resultList = new ArrayList<>();
        //只取set中有的，取出来之后在Set中remove
        queue.poll(getWindowNum()).stream().filter(t -> uniqueKeySet.contains(t.getUniqueKey())).forEach(t -> {
            resultList.add(t);
            uniqueKeySet.remove(t.getUniqueKey());
        });
        return resultList;
    }

    /**
     * queue名
     */
    private String getQueueName() {
        return getTaskName() + "_QUEUE";
    }

    /**
     * set名
     */
    private String getSetName() {
        return getTaskName() + "_SET";
    }
}
