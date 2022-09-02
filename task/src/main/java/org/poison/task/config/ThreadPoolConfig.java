package org.poison.task.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jodd.util.concurrent.ThreadFactoryBuilder;

@Slf4j
@Configuration
public class ThreadPoolConfig {

    @Bean(value = "testThreadPool")
    ExecutorService testThreadPool() {
        ThreadFactory taskThreadFactory = new ThreadFactoryBuilder().setNameFormat("test").get();
        return new ThreadPoolExecutor(5, 10, 60L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1000), taskThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

}
