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
        return StringUtils.isNotBlank(getTokenValue(data));
    }

    public String getUserId(SocketIOClient client) {
        Object o = StpUtil.getLoginIdByToken(getTokenValue(client));
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    private String getTokenValue(SocketIOClient client) {
        return getTokenValue(client.getHandshakeData());
    }

    private String getTokenValue(HandshakeData data) {
        // 有可能在header也有可能在param里
        String headerToken = data.getHttpHeaders().get(tokenName);
        if (StringUtils.isNotBlank(headerToken)) {
            return headerToken;
        }
        return data.getSingleUrlParam(tokenName);
    }
}
