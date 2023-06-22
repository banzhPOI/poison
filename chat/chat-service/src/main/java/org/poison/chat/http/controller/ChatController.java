package org.poison.chat.http.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.chat.client.ChatClient;
import org.poison.chat.core.req.ChatMessage;
import org.poison.chat.http.service.ChatService;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChatController implements ChatClient {

    @Resource
    private ChatService chatService;

    public void sendMessage(ChatMessage chatMessage) {
        // 广播消息给所有连接的客户端
        chatService.sendMessageToAll(chatMessage.getMessage());
    }
}
