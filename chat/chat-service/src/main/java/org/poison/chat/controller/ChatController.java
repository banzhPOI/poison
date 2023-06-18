package org.poison.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChatController implements ChatClient {


    @Override
    public void test() {

    }
}
