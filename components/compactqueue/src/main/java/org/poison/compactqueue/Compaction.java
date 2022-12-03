package org.poison.compactqueue;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.redisson.api.RQueue;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


/**
 * 重写"任务名",“处理任务"两个方法
 * 任务窗口数量和消费任务延迟也可以重写
 * 原理：
 * 1.插入任务的时候同时插入RList和RSet
 * 2.取出任务的时候从RList取出来的时候同时从RSet中移除这条数据，如果移除失败，就丢弃这条数据，证明之前消费过了
 */

public abstract class Compaction<T extends BaseTask> implements SchedulingConfigurer {

    /**
     * 默认任务窗口数量 1000
     */
    private final static int DEFAULT_TASK_WINDOW_NUM = 1000;

    /**
     * 默认消费延迟时间 1000毫秒
     */
    private final static int DEFAULT_DELAY_TIME = 1000;

    @Resource
    private RedissonClient redisson;

    /**
     * 每次取任务的窗口数量
     * 可被重写
     * 默认1000
     */
    protected int getWindowNum() {
        return DEFAULT_TASK_WINDOW_NUM;
    }

    /**
     * 获取消费延迟时间
     * 可被重写
     * 默认1000毫秒
     */
    protected long getDelay() {
        return DEFAULT_DELAY_TIME;
    }

    /**
     * 任务名
     */
    protected abstract String getTaskName();

    /**
     * 重写这个方法，处理任务
     */
    protected abstract void handle(List<T> list);

    private RQueue<T> queue;

    private RSet<String> uniqueKeySet;

    /**
     * 队列初始化
     */
    @PostConstruct
    protected void postConstruct() {
        queue = redisson.getQueue(getQueueName());
        uniqueKeySet = redisson.getSet(getSetName());
    }

    /**
     * 任务入队
     */
    public void add(T t) {
        uniqueKeySet.add(t.getUniqueKey());
        queue.add(t);
    }

    /**
     * 任务消费
     */
    private void consumeTask() {
        handle(get());
    }

    /**
     * lpop不会有并发问题
     */
    public List<T> get() {
        //只取set中有的，取出来之后在Set中remove
        return queue.poll(getWindowNum()).stream().filter(t -> uniqueKeySet.remove(t.getUniqueKey())).collect(Collectors.toList());
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

    /**
     * 配置任务消费延迟
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newSingleThreadScheduledExecutor());
        taskRegistrar.addTriggerTask(
                this::consumeTask, context -> {
                    Optional<Instant> lastCompletionTime = Optional.ofNullable(context.lastCompletion());
                    return lastCompletionTime.orElseGet(Instant::now).plusMillis(getDelay());
                });
    }
}
