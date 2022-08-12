package org.poison.redisson;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private RedissonClient redisson;

    private RMapCache<String, RMapCache<String, Long>> rMapCacheRMapCache;

    @PostConstruct
    public void init() {
        rMapCacheRMapCache = redisson.getMapCache("FIRST");
    }

    public RMapCache<String, Long> getSecond(String secondName) {
        RMapCache<String, Long> second = rMapCacheRMapCache.get(secondName);
        if (second == null) {
            second = redisson.getMapCache(secondName);
        }
        return second;
    }

    public Long getThird(String secondName, String thirdName) {
        RMapCache<String, Long> second = getSecond(secondName);
        Long third = second.get(thirdName);
        return third == null ? 0 : third;
    }

    @PostMapping(value = "put")
    public void put() {
        String secondName = "SECOND";
        RMapCache<String, Long> second = getSecond(secondName);
        second.put("THIRD", 1L, 10, TimeUnit.SECONDS);
        rMapCacheRMapCache.put(secondName, second);
    }


    @PostMapping(value = "get")
    public Long get() {
        String secondName = "SECOND";
        String thirdName = "SECOND";
        return getThird(secondName, thirdName);
    }
}
