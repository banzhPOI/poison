package org.poison.web.component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Set;

@Slf4j
@Component
@DependsOn("redissonClient")
@Order(10)
public class ChatHandler implements WebSocketHandler {

    @Resource
    private RedissonClient redissonClient;

    private RMapCache<String, WebSocketSession> SESSIONS;

    @PostConstruct
    public void init() {
        SESSIONS = redissonClient.getMapCache("ws:chat");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("连接成功" + session.getId());
        SESSIONS.put(session.getId(), session);
        log.info("当前在线人数：" + SESSIONS.size());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        log.info("接收消息" + session.getId());
        String msg = message.getPayload().toString();
        log.info(msg);
//        session.sendMessage(message);
        sendMessageToAllUsers(session.getId() + ":" + msg);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("连接出错" + session.getId());
        if (!session.isOpen()) {
            SESSIONS.remove(session.getId());
            session.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        log.info("关闭连接" + session.getId());
        if (!session.isOpen()) {
            SESSIONS.remove(session.getId());
            log.info("当前在线人数：" + SESSIONS.size());
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * sendMessageToUser:发给指定用户
     */
    public void sendMessageToUser(String userId, String contents) {
        WebSocketSession session = SESSIONS.get(userId);
        if (session != null && session.isOpen()) {
            try {
                TextMessage message = new TextMessage(contents);
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sendMessageToAllUsers:发给所有的用户
     */
    public void sendMessageToAllUsers(String contents) {
        Set<String> userIds = SESSIONS.keySet();
        for (String userId : userIds) {
            this.sendMessageToUser(userId, contents);
        }
    }
}