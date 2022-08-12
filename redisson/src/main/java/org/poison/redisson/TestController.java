package org.poison.redisson;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RMapCache;
import org.redisson.api.RSet;
import org.redisson.api.RSetCache;
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
    private RedissonClient redisson;

    private RMapCache<String, RSetCache<String>> rMapCacheRMapCache;

    @PostConstruct
    public void init() {
        rMapCacheRMapCache = redisson.getMapCache("SHOP");
    }

    public RSetCache<String> getShop(String shopName) {
        RSetCache<String> shop = rMapCacheRMapCache.get(shopName);
        if (shop == null) {
            shop = redisson.getSetCache(shopName);
        }
        return shop;
    }

    public Long getSessionNum(String shopName, String sessionName) {
        RAtomicLong num = redisson.getAtomicLong(sessionName + "_LONG");
        return num == null ? 0 : num.get();
    }


    public void putSessionNum(String shopName, String sessionName, Long sessionNum) {
        RMapCache<String, RAtomicLong> shop = getShop(shopName);
        RAtomicLong atomicLong = redisson.getAtomicLong(sessionName + "_LONG");
        atomicLong.set(sessionNum);
        shop.put(sessionName, atomicLong, 60, TimeUnit.SECONDS);
    }

    @PostMapping(value = "put")
    public void put() {
        String shopName = "SECOND";
        String sessionName = "THIRD";
        putSessionNum(shopName, sessionName, 1L);
    }


    @PostMapping(value = "get")
    public Long get() {
        String shopName = "SECOND";
        String sessionName = "THIRD";
        return getSessionNum(shopName, sessionName);
    }
}
