package org.poison.compactqueue.ack;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.poison.compactqueue.BaseTask;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;


@Slf4j
@Aspect
public class AopRequeue implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 处理异常，并重新加入队列，重试5次
     *
     * @param pj
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.poison.compactqueue.ack.ExceptionRequeue)")
    public Object aroundAop(ProceedingJoinPoint pj) throws Throwable {
        Object result = null;
        try {
            result = pj.proceed();
        } catch (Exception e) {
            MethodSignature methodSignature = (MethodSignature) pj.getSignature();
            ExceptionRequeue exceptionRequeue = methodSignature.getMethod().getAnnotation(ExceptionRequeue.class);
            Object o = context.getBean(exceptionRequeue.queueClass());
            Method method = o.getClass().getMethod("add", BaseTask.class);
            BaseTask baseTask = (BaseTask) pj.getArgs()[0];
            log.warn("do task: {} exception: {} retryTimes: {}", baseTask.getUniqueKey(), e.getMessage(), baseTask.getRetryTimes());
            baseTask.setRetryTimes(baseTask.getRetryTimes() == null ? 1 : baseTask.getRetryTimes() + 1);
            if (baseTask.getRetryTimes() <= 5) {
                method.invoke(o, baseTask);
            }
        }
        return result;
    }
}