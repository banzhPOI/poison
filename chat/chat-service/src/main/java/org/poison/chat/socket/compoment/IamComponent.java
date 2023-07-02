package org.poison.chat.socket.compoment;

import cn.dev33.satoken.stp.StpUtil;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IamComponent {

    @Value("${sa-token.token-name}")
    private String tokenName;

    public boolean checkLogin(HandshakeData data) {
        return StringUtils.isNotBlank(data.getSingleUrlParam(tokenName));
    }

    public String getUserId(SocketIOClient client) {
        Object o = StpUtil.getLoginIdByToken(getTokenValue(client));
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    private String getTokenValue(SocketIOClient client) {
        return client.getHandshakeData().getSingleUrlParam(tokenName);
    }
}
