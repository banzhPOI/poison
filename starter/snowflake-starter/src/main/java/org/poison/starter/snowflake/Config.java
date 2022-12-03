package org.poison.starter.snowflake;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;


@Slf4j
public class Config {

    private static final int MAX_WORKER_COUNT = 1024;
    private static final String REDIS_WORK_ID_KEY = "worker-id-key";
    private final long workerId;

    Config(RedissonClient redissonClient) {
        this.workerId = initWorkerId(redissonClient);
    }

    /**
     * 雪花算法实现生成唯一单号
     */
    @Bean
    IdWorker idWorker() {
        log.info("init idWorker, workerId:" + workerId);
        return new IdWorker(workerId);
    }

    /**
     * 获取当前服务实例编号，作为分布式Id生成器机器位
     */
    private int initWorkerId(RedissonClient redissonClient) {
        int value;
        try {
            Long incrementValue = redissonClient.getAtomicLong(REDIS_WORK_ID_KEY).addAndGet(1L);
            value = incrementValue.intValue() % MAX_WORKER_COUNT;
        } catch (Exception e) {
            value = RandomUtils.nextInt(0, MAX_WORKER_COUNT);
            log.error("Init workerId failed. Supplementary random number:" + value, e);
        }
        return value;
    }
}
