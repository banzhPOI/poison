package org.poison.redisson;

import lombok.extern.slf4j.Slf4j;

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

    @Resource
    private RedissonClient redisson;

    RDeque<String> queue;
    RList<String> list;

    @PostConstruct
    public void init() {
        queue = redisson.getDeque("anyDeque1");
        list = redisson.getList("any");

    }


    @PostMapping(value = "put")
    public void put() {
        queue.add("abc");
        queue.add("abc");
        queue.add("bcd");
        queue.add("cde");
        queue.add("abc");
        queue.add("abc");
        queue.add("bcd");
        queue.add("cde");
        queue.add("abc");
        queue.add("abc");
        queue.add("bcd");
        queue.add("cde");
        queue.add("abc");
        queue.add("abc");
        queue.add("bcd");
        queue.add("cde");
        for (int i = 0; i < 10; i++) {
            log.info("size: " + queue.size());
            List<String> strings = queue.poll(2);
//            String str = queue.poll();

//           queue.removeAll("abc");
            queue.remove("abc");

            strings.forEach(it -> {
                log.info(it);

            });
        }
    }


    @PostMapping(value = "put2")
    public void put2() {
        list.add("abc");
        list.add("abc");
        list.add("bcd");
        list.add("cde");
        list.add("abc");
        list.add("abc");
        list.add("bcd");
        list.add("cde");
        list.add("abc");
        list.add("abc");
        list.add("bcd");
        list.add("cde");
        list.add("abc");
        list.add("abc");
        list.add("bcd");
        list.add("cde");
        for (int i = 0; i < 10; i++) {
            log.info("size: " + list.size());
            List<String> strings = new ArrayList<>();
//            strings.add("abc");
//            list.removeAll(strings);
            list.remove("abc");
            log.info("size2: " + list.size());

        }
    }

    private RScript getScript() {
        return redisson.getScript(StringCodec.INSTANCE);
    }

    @PostMapping(value = "add1")
    public void add1() {
        String luaScript = String.join("\n",
            "redis.call('HSET', KEYS[1], ARGV[1],ARGV[2])"
        );

        getScript().eval(RScript.Mode.READ_WRITE,
            luaScript,
            RScript.ReturnType.INTEGER,
            List.of("banzhTest1"),
            "key1",
            "value1"
        );
        getScript().eval(RScript.Mode.READ_WRITE,
            luaScript,
            RScript.ReturnType.INTEGER,
            List.of("banzhTest1"),
            "key2",
            "value2"
        );

    }
    @PostMapping(value = "del")
    public void del() {
        String luaScript = String.join("\n",
            "redis.call('HDEL', KEYS[1], ARGV[1])"
        );

        getScript().eval(RScript.Mode.READ_WRITE,
            luaScript,
            RScript.ReturnType.INTEGER,
            List.of("banzhTest1"),
            "key1"
        );


    }
}
