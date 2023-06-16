package org.poison.account.pojo.entity;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.poison.account.core.enums.UserStatus;
import org.poison.account.core.req.UserRegisterRequest;
import org.poison.starter.snowflake.IdUtils;

import java.time.Instant;

@Data
public class User {

    private String id;

    private String nickname;

    private String email;

    private String mobile;

    private UserStatus status;

    private String password;

    private Instant createTime;

    private Instant updateTime;

    public static User fromRegisterRequest(UserRegisterRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setId("U" + IdUtils.get());
        user.setNickname(StringUtils.defaultString(request.getNickname()));
        user.setEmail(StringUtils.defaultString(request.getEmail()));
        user.setMobile(StringUtils.defaultString(request.getMobile()));
        user.setStatus(UserStatus.NORMAL);
        user.setPassword(request.getPassword());
        return user;
    }
}
