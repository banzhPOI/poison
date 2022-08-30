package org.poison.merge.ack;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.poison.merge.set.SetMerger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Aspect
@Component
public class AopAck {

    @Resource
    private SetMerger setMerger;

    @Pointcut("@annotation(org.poison.merge.ack.UseAck)")
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