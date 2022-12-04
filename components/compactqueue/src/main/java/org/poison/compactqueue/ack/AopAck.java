package org.poison.compactqueue.ack;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.poison.compactqueue.BaseTask;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;


@Slf4j
@Aspect
public class AopAck implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    @Pointcut("@annotation(org.poison.compactqueue.ack.UseAck)")
    public void aopPoint() {
    }


    @Around("aopPoint()")
    public void aroundAop(ProceedingJoinPoint pj) throws Throwable {
        try {
            pj.proceed();
        } catch (Exception e) {
            MethodSignature methodSignature = (MethodSignature) pj.getSignature();
            UseAck useAck = methodSignature.getMethod().getAnnotation(UseAck.class);
            Object o = context.getBean(useAck.beanName());
            Method method = o.getClass().getMethod("add", BaseTask.class);
            for (Object arg : pj.getArgs()) {
                method.invoke(o, arg);
            }
        }
    }


}