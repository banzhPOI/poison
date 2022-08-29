package org.poison.task.distinct;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.poison.common.exception.BizException;
import org.poison.merge.ack.UseAck;
import org.poison.merge.set.SetMerger;
import org.poison.task.task.Task;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.Resource;

@Slf4j
@Component
public class SetHandler extends SetMerger<Task> {

    @Resource
    private ObjectMapper objectMapper;

    private static final int INTERVAL_TIME = 1000;

    private static final int WINDOW_NUM = 100;

    private static final String TASK_NAME = "DEFAULT_TASK_2";


    @Override
    protected int getWindowNum() {
        return WINDOW_NUM;
    }

    @Override
    protected String getTaskName() {
        return TASK_NAME;
    }


    /**
     * todo 失败的消息重写加入队列
     */
//    @Scheduled(fixedDelay = INTERVAL_TIME)
    @UseAck
    @Override
    public void handleTask() {
        List<Task> taskList = get();
        for (Task task : taskList) {
            handle(task);
        }
    }

    @SneakyThrows
    public void handle(Task task){
        log.info("handle task : {}", objectMapper.writeValueAsString(task));
        throw new BizException("wtf");
    }
}
