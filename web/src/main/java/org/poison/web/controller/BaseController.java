package org.poison.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.UserClient;
import org.poison.account.core.req.UserGetRequest;
import org.poison.account.core.resp.UserVO;
import org.poison.common.constant.GlobalConstant;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserClient userClient;

    protected UserVO getCurrentUser() {
        String userId = request.getHeader(GlobalConstant.CURRENT_USER);
        if (userId == null) {
            return null;
        }
        UserGetRequest userGetRequest = new UserGetRequest();
        userGetRequest.setUserId(userId);
        return userClient.findUserById(userGetRequest);
    }

    protected String getCurrentUserId() {
        return request.getHeader(GlobalConstant.CURRENT_USER);
    }
}
