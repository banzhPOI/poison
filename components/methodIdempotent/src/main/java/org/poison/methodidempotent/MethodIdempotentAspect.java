package org.poison.methodidempotent;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RSetCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


@Slf4j
@Aspect
public class MethodIdempotentAspect {

    @Value("${spring.application.name}")
    private String appName;

    @Resource
    private RedissonClient redisson;

    @Resource
    private ObjectMapper objectMapper;

    private RSetCache<Integer> apiCache;

    @PostConstruct
    public void initApiCache() {
        apiCache = redisson.getSetCache(appName + "_API_CACHE");
    }

    @Around("@annotation(org.poison.methodidempotent.MethodIdempotent)")
    public Object aroundAop(ProceedingJoinPoint pj) throws Throwable {
        MethodSignature signature = (MethodSignature) pj.getSignature();
        Method method = signature.getMethod();
        MethodIdempotent annotation = method.getAnnotation(MethodIdempotent.class);


        MethodObject methodObject = new MethodObject();
        methodObject.setMethodName(pj.getTarget().getClass().getName() + "." + signature.getName());
        methodObject.setArgs(pj.getArgs());
        Integer hashCode = objectMapper.writeValueAsString(methodObject).hashCode();

        if (apiCache.add(hashCode, annotation.expire(), TimeUnit.SECONDS)) {
            return pj.proceed();

        } else {
            log.info("重复了");
        }

        return null;

    }
}