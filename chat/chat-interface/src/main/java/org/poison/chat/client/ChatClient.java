package org.poison.chat.client;

import org.poison.chat.core.req.ChatMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "chat-service", contextId = "chat")
public interface ChatClient {

    @PostMapping("chat/sendMessage")
    void sendMessage(@RequestBody ChatMessage chatMessage);
}
