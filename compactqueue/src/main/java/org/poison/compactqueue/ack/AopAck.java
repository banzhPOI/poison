package org.poison.compactqueue.ack;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.poison.compactqueue.Compaction;
import org.redisson.api.RMapCache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Aspect
@Component
public class AopAck {

    @Resource
    private Compaction compaction;
RMapCache
    @Pointcut("@annotation(org.poison.compactqueue.ack.UseAck)")
    public void aopPoint() {
    }

    @Around("aopPoint()")
    public void aroundAop(ProceedingJoinPoint pj) throws Throwable {
        try {
            pj.proceed();
        } catch (Exception e) {
            log.warn("task handle failed:", e);
//            setMerger.add();
        }
    }
}