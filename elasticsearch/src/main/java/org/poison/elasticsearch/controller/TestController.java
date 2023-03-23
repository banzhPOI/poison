package org.poison.elasticsearch.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("es")
public class TestController {

    @Resource
    private ElasticsearchClient elasticsearchClient;


    @SneakyThrows
    @PostMapping(value = "add")
    public void add() {
        CreateIndexResponse createIndexResponse = null;
        createIndexResponse = elasticsearchClient.indices()
                .create(createIndexRequest ->
                        createIndexRequest.index("elasticsearch-client")
                );

        log.info("== {} 索引创建是否成功: {}", "elasticsearch-client", createIndexResponse.acknowledged());

    }

    @PostMapping(value = "get")
    public void get() {
    }
}
