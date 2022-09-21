package org.poison.spring.elasticsearch.starter;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.elasticsearch")
public class ElasticsearchConfig {

    /**
     * 协议
     */
    private String schema = "http";

    /**
     * 地址
     */
    private String address;

    /**
     * 端口号
     */
    private int port;

    /**
     * 的用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接超时时间
     */
    private int connectTimeout = 10000;

    /**
     * Socket连接超时时间
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
}
