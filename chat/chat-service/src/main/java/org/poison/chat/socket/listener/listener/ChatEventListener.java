package org.poison.chat.socket.listener.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
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


    @OnConnect
    public void onConnect(SocketIOClient client) {
        log.info("Client connected: " + client.getSessionId());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("Client disconnected: " + client.getSessionId());
    }

    @OnEvent("chatMessage")
    public void onChatMessage(SocketIOClient client, String message, AckRequest ackRequest) {
        log.info("Message received from client " + client.getSessionId() + ": " + message);
        service.sendMessageToAll(message);
    }
}

