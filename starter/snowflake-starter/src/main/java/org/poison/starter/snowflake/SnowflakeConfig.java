package org.poison.starter.snowflake;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;


@Slf4j
public class SnowflakeConfig {

    @Resource
    private RedissonClient redissonClient;

    private static final long DATACENTER_ID = 1;
    private static final int MAX_WORKER_COUNT = 3;
    private static final String REDIS_WORK_ID_KEY = "worker-id-key";
    private final long workerId;

    SnowflakeConfig() {
        this.workerId = initWorkerId();
    }

    /**
     * 雪花算法实现生成唯一单号
     */
    @Bean
    SnowflakeIdWorker idWorker() {
        log.info("init idWorker, workerId:" + workerId + ", datacenterId:" + DATACENTER_ID);
        return new SnowflakeIdWorker(workerId, DATACENTER_ID);
    }

    /**
     * 获取当前服务实例编号，作为分布式Id生成器机器位
     */
    private int initWorkerId() {
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
