package org.poison.starter.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Slf4j
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config conf = new Config();
        //单节点模式
        SingleServerConfig singleServerConfig = conf.useSingleServer();
        //设置连接地址
        singleServerConfig.setAddress("redis://"+host);
        //设置连接密码
        singleServerConfig.setPassword(password);
        //使用json序列化方式
        Codec codec = new JsonJacksonCodec();
        conf.setCodec(codec);
        return Redisson.create(conf);
    }

}
