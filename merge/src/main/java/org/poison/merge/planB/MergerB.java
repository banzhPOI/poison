package org.poison.merge.planB;

import org.poison.merge.Merger;
import org.redisson.api.RList;
import org.redisson.api.RQueue;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public abstract class MergerB<T> implements Merger<T> {

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

    private RList<T> taskList;

    private RSet<T> taskSet;

    @PostConstruct
    private void postConstruct() {
        taskList = redissonClient.getList(getListName());
        taskSet = redissonClient.getSet(getSetName());
    }


    @Override
    public void add(T t) {
        taskList.add(t);
        taskSet.add(t);
    }

    @Override
    public List<T> get() {
        List<T> list;
        List<T> resultList = new ArrayList<>();
        if (CollectionUtils.isEmpty(taskList)) {
            return new ArrayList<>();
        }
        if (taskList.size() <= getWindowNum()) {
            list = taskList;
            taskList.clear();
        } else {
            list = taskList.subList(0, getWindowNum());
            taskList.p(0,getWindowNum());
        }
        //只取set中有的
        for (T t : list) {
            if (taskSet.contains(t)) {
                resultList.add(t);
                //并且在Set中remove
                taskSet.remove(t);
            }
        }
        return resultList;
    }

    /**
     * list名
     */
    private String getListName(){
        return getTaskName()+"_LIST";
    }

    /**
     * set名
     */
    private String getSetName(){
        return getTaskName()+"_SET";
    }

}
