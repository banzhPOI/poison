package org.poison.chat.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "chat-service", contextId = "chat")
public interface ChatClient {

    @PostMapping("chat/test")
    void test();
}
