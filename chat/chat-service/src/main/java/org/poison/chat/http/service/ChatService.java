package org.poison.chat.http.service;

public interface ChatService {

    void sendMessageToAll(String message);

    void sendMessageToUser(String userId, String message);

}
