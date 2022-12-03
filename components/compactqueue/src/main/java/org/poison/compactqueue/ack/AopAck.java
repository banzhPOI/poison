package org.poison.compactqueue.ack;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Slf4j
@Aspect
public class AopAck {

    @Pointcut("@annotation(org.poison.compactqueue.ack.UseAck)")
    public void aopPoint() {
    }


    @Around("aopPoint()")
    public void aroundAop(ProceedingJoinPoint pj) throws Throwable {
        try {
            pj.proceed();
        } catch (Exception e) {
            log.warn("task handle failed:", e);
        }
    }
}