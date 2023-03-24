package org.poison.account.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.poison.account.core.req.LoginRequest;
import org.poison.account.core.resp.LoginResponse;
import org.poison.account.service.IamService;
import org.poison.common.exception.BizException;
import org.springframework.stereotype.Service;

@Service
public class IamServiceImpl implements IamService {

    @Override
    public LoginResponse doLogin(LoginRequest loginRequest) {
        if (!"zhang".equals(loginRequest.getUsername()) && "123456".equals(loginRequest.getPassword())) {
            throw new BizException("登录失败");
        }
        StpUtil.login(10001);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return new LoginResponse(tokenInfo.getTokenName(), tokenInfo.getTokenValue());
    }
}
