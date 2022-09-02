package org.poison.elasticsearch.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
public class ElasticsearchConfig {


    /**
     * 协议
     */
    private String schema = "http";

    /**
     * 集群地址，如果有多个用“,”隔开
     */
    private String address = "es-cn-i7m2um2wh00042xyw.public.elasticsearch.aliyuncs.com:9200";

    /**
     * 连接超时时间
     */
    private int connectTimeout = 10000;

    /**
     * Socket 连接超时时间
     */
    private int socketTimeout = 15000;

    /**
     * 获取连接的超时时间
     */
    private int connectionRequestTimeout = 20000;

    /**
     * 最大连接数
     */
    private int maxConnectNum = 100;

    /**
     * 最大路由连接数
     */
    private int maxConnectPerRoute = 100;

    /**
     * 连接ES的用户名
     */
    private String username = "elastic";

    /**
     * 数据查询的索引
     */
    private String index;

    /**
     * 密码
     */
    private String passwd;

}
