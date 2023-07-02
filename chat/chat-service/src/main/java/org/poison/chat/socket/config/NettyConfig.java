package org.poison.chat.socket.config;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.naming.NamingService;
import jakarta.annotation.Resource;
import org.poison.common.util.NetworkUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * 作用是启动后给websocket端口注册服务
 */
@Component
public class NettyConfig implements ApplicationRunner {

    @Value("${spring.netty.application.name}")
    private String nettyServiceName;

    @Value("${spring.netty.application.port}")
    private Integer nettyServicePort;

    @Resource
    private NacosServiceManager nacosServiceManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        NamingService namingService = nacosServiceManager.getNamingService();
        namingService.registerInstance(nettyServiceName, NetworkUtils.getLocalHostExactAddress().getHostAddress(), nettyServicePort);
    }
}
