package org.poison.merge.schedule;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class Schedule implements SchedulingConfigurer {

    public static String DEFAULT_CORN = "0/3 * * * * *";
    //##动态传参要给默认值。
    public static String corn = DEFAULT_CORN;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(() -> {
            // logger.info("定时任务逻辑");
        }, triggerContext -> {
            //任务触发，可修改任务的执行周期
            CronTrigger cronTrigger = new CronTrigger(corn);
            return cronTrigger.nextExecutionTime(triggerContext);
        });
    }
}