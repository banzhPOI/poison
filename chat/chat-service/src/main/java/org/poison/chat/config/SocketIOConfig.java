package org.poison.chat.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {

    @Value("${socketio.host:127.0.0.1}")
    private String host;

    @Value("${socketio.port:8081}")
    private int port;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);

        SocketIOServer server = new SocketIOServer(config);
        server.start();
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer server) {
        return new SpringAnnotationScanner(server);
    }
}
