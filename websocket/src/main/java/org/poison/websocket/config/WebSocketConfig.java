package org.poison.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "myHandler/{ID}")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
    public WebSocketHandler myHandler() {
        return new WebsocketHandler();
    }

}