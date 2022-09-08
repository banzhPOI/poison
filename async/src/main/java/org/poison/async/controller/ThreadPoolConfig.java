package org.poison.async.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ThreadPoolConfig {

    @Bean(value = "testThreadPool")
    ExecutorService testThreadPool() {
        return  new ThreadPoolExecutor(52, 64, 60L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(2048),
            runnable -> new Thread(runnable, "test-" + runnable.hashCode()),
            (r, executor) -> r.run());
    }

}
