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

    private RSet<String> taskKeySet;

    @PostConstruct
    private void postConstruct() {
        taskQueue = redissonClient.getQueue(getQueueName());
        taskKeySet = redissonClient.getSet(getSetName());
    }

    public void add(T t) {
        taskQueue.add(t);
        taskKeySet.add(t.getUniqueKey());
    }

    /**
     * lpop不会有并发问题
     */
    public List<T> get() {
        List<T> resultList = new ArrayList<>();
        //只取set中有的，取出来之后在Set中remove
        taskQueue.poll(getWindowNum()).stream().filter(t -> taskKeySet.contains(t.getUniqueKey())).forEach(t -> {
            resultList.add(t);
            taskKeySet.remove(t.getUniqueKey());
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
