package org.poison.chat.socket.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.chat.socket.compoment.IamComponent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyConnectListener implements ConnectListener {
    @Resource
    private IamComponent iamComponent;

    @Override
    public void onConnect(SocketIOClient client) {
        String userId = iamComponent.getUserId(client);
        log.info("Client connected, clientId: {} userId: {}", client.getSessionId(), userId);
    }
}

