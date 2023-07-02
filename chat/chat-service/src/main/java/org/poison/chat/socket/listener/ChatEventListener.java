package org.poison.chat.socket.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.chat.http.service.ChatService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatEventListener {

    @Resource
    private ChatService service;

    @OnEvent("chatMessage")
    public void onChatMessage(SocketIOClient client, String message, AckRequest ackRequest) {
        log.info("Message received from client " + client.getSessionId() + ": " + message);
        service.sendMessageToAll(message);
    }
}

