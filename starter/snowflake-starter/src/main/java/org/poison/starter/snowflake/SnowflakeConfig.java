package org.poison.starter.snowflake;


import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;


public class SnowflakeConfig {

    private static final Logger log = LoggerFactory.getLogger(SnowflakeConfig.class);


    private static final long DATACENTER_ID = 1;
    private static final int MAX_WORKER_COUNT = 3;
    private static final String REDIS_WORK_ID_KEY = "genie-ims-redis-work-id-key";
    private final long workerId;


    SnowflakeConfig(StringRedisTemplate redisTemplate) {
        this.workerId = initWorkerId(redisTemplate);
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
    private int initWorkerId(StringRedisTemplate redisTemplate) {
        int value;
        try {
            Long incrementValue = redisTemplate.opsForValue().increment(REDIS_WORK_ID_KEY);
            if (incrementValue == null) {
                throw new IllegalAccessException("Increase number is null.[redisTemplate.opsForValue().increment(" + REDIS_WORK_ID_KEY + ")]");
            }
            value = incrementValue.intValue() % MAX_WORKER_COUNT;
        } catch (Exception e) {
            value = RandomUtils.nextInt(0, MAX_WORKER_COUNT);
            log.error("Init workerId failed. Supplementary random number:" + value, e);
        }
        return value;
    }
}
