package org.poison.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WebsocketHandler extends TextWebSocketHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Map<String, String> map = objectMapper.readValue(payload, HashMap.class);
        log.info("=====接受到的数据"+map);
        session.sendMessage(new TextMessage("服务器返回收到的信息," + payload));
    }
}
