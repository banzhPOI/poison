package org.poison.chat.socket.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {

    @Value("${spring.netty.application.port}")
    private Integer nettyServicePort;

    @Bean
    public SocketIOServer socketIOServer() throws UnknownHostException {
        Configuration config = new Configuration();
        config.setHostname(InetAddress.getLocalHost().getHostAddress());
        config.setPort(nettyServicePort);
        SocketIOServer server = new SocketIOServer(config);
        server.start();
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer server) {
        return new SpringAnnotationScanner(server);
    }
}
