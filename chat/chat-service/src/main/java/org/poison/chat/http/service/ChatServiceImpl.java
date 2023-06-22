package org.poison.chat.http.service;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private SocketIOServer server;

    @Override
    public void sendMessageToAll(String message) {
        // 广播消息给所有连接的客户端
        server.getBroadcastOperations().sendEvent("chatMessage", message);
    }

    @Override
    public void sendMessageToUser(String userId, String message) {
    }
}
