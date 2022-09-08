package org.poison.elasticsearch.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("es")
public class TestController {

    @Resource
    private ElasticsearchClient elasticsearchClient;


    @PostMapping(value = "add")
    public void add() {
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = elasticsearchClient.indices()
                .create(createIndexRequest ->
                    createIndexRequest.index("elasticsearch-client")
                );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("== {} 索引创建是否成功: {}", "elasticsearch-client", createIndexResponse.acknowledged());

    }

    @PostMapping(value = "get")
    public void get() {
    }
}