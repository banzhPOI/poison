package org.poison.merge.ack;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AopAck {

    @Pointcut("@annotation(org.poison.merge.ack.UseAck)")
    public void aopPoint() {
    }

    @Around("aopPoint()")
    public void aroundAop(ProceedingJoinPoint pj) throws Throwable {
        try {
            log.info("aop enabled");
            pj.proceed();
        } catch (Exception e) {
            log.warn("task handle failed: {}", e);
        }
    }
}