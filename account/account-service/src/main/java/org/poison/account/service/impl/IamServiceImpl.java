package org.poison.account.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import org.poison.account.core.req.LoginRequest;
import org.poison.account.core.req.LogoutRequest;
import org.poison.account.core.resp.LoginResponse;
import org.poison.account.pojo.dto.UserDTO;
import org.poison.account.service.IamService;
import org.poison.account.service.UserService;
import org.poison.common.exception.BizException;
import org.springframework.stereotype.Service;

@Service
public class IamServiceImpl implements IamService {

    @Resource
    private UserService userService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        if (!userService.checkUser(loginRequest.getUsername(), loginRequest.getPassword())) {
            throw new BizException("用户不存在或密码错误");
        }
        UserDTO user = userService.findUserByUsername(loginRequest.getUsername());
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return new LoginResponse(user.getId(), tokenInfo.getTokenName(), tokenInfo.getTokenValue());
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        StpUtil.logout(logoutRequest.getUserId());
    }
}
