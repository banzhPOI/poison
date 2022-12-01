package org.poison.redisson;

import lombok.extern.slf4j.Slf4j;

import org.poison.spring.snowflake.starter.SnowflakeIdWorker;
import org.redisson.api.RDeque;
import org.redisson.api.RList;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

//    @Resource
//    private SnowflakeIdWorker idWorker;

    RDeque<String> queue;
    RList<String> list;

    @PostConstruct
    public void init() {

//       log.info(idWorker.nextId()+"");

    }


}
