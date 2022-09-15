package org.poison.merge;

import org.redisson.api.RQueue;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 重写"任务名",取任务窗口数量","取任务线程池数量"三个方法
 * 原理：
 * 1.插入任务的时候同时插入RList和RSet
 * 2.取出任务的时候从RList取出来的时候同时从RSet中移除这条数据，如果移除失败，就丢弃这条数据，证明之前消费过了
 */

public abstract class Merger<T extends BaseTask> {

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
     * 示例：
     * <pre class="code">
     * &#064;Override
     * public void handleTask() {
     *      List<Task> taskList = get();
     *      for (Task task : taskList) {
     *      }
     * }</pre>
     */
    protected abstract void handleTask();

    private RQueue<T> queue;

    private RSet<String> uniqueKeySet;

    @PostConstruct
    protected void postConstruct() {
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
