package org.poison.starter.snowflake;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class IdUtils implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        IdUtils.context=applicationContext;
    }

    public static long get(){
        return context.getBean(IdWorker.class).nextId();
    }
}
