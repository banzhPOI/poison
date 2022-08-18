package org.poison.redisson;

import lombok.extern.slf4j.Slf4j;

import org.redisson.api.RMapCache;
import org.redisson.api.RSetCache;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private RedissonClient redisson;

    private RMapCache<String, RSetCache<String>> rMapCacheRMapCache;


    @PostMapping(value = "put")
    public void put() {

    }

}
