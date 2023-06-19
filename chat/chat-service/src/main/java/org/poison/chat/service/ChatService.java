package org.poison.chat.service;

public interface ChatService {

    void sendMessageToAll(String message);

    void sendMessageToUser(String userId, String message);

}
