package org.poison.compactqueue.ack;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.poison.compactqueue.BaseTask;
import org.poison.compactqueue.Compaction;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Aspect
@Component
public class AopAck {

    @Resource
    private Compaction<BaseTask> compaction;

    @Pointcut("@annotation(org.poison.compactqueue.ack.UseAck)")
    public void aopPoint() {
    }

    @Around("aopPoint()")
    public void aroundAop(ProceedingJoinPoint pj) throws Throwable {
        try {
            pj.proceed();
        } catch (Exception e) {
            log.warn("task handle failed:", e);
            Object[] args = pj.getArgs();
            BaseTask task = (BaseTask) args[0];
            compaction.add(task);
        }
    }
}