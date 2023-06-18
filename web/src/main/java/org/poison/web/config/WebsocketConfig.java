package org.poison.web.config;


import jakarta.annotation.Resource;
import org.poison.web.component.ChatHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Component

public class WebsocketConfig implements WebSocketConfigurer {

    @Resource
    private ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/ws/chat").setAllowedOrigins("*").addInterceptors();
    }
}
