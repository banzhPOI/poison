package org.poison.chat.socket.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.chat.socket.compoment.IamComponent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SaTokenAuthListener implements AuthorizationListener {

    @Resource
    private IamComponent iamComponent;

    @Override
    public boolean isAuthorized(HandshakeData data) {
        return iamComponent.checkLogin(data);
    }
}

