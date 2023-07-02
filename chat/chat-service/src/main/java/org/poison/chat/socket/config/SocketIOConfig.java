package org.poison.chat.socket.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import jakarta.annotation.Resource;
import org.poison.chat.socket.listener.MyConnectListener;
import org.poison.chat.socket.listener.MyDisconnectListener;
import org.poison.chat.socket.listener.SaTokenAuthListener;
import org.poison.common.util.NetworkUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {

    @Value("${spring.netty.application.port}")
    private Integer nettyServicePort;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private SaTokenAuthListener saTokenAuthListener;

    @Resource
    private MyConnectListener connectListener;

    @Resource
    private MyDisconnectListener disconnectListener;

    @Bean
    public SocketIOServer socketIOServer() throws IOException {
        Configuration config = new Configuration();
        config.setHostname(NetworkUtils.getLocalHostExactAddress().getHostAddress());
        config.setPort(nettyServicePort);
        config.setTransports(Transport.WEBSOCKET); // 只启用 WebSocket 传输方式
        // 登录认证
        config.setAuthorizationListener(saTokenAuthListener);
        config.setStoreFactory(new RedissonStoreFactory(redissonClient));
        SocketIOServer server = new SocketIOServer(config);
        // lister
        server.addConnectListener(connectListener);
        server.addDisconnectListener(disconnectListener);

        server.start();
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer server) {
        return new SpringAnnotationScanner(server);
    }
}
