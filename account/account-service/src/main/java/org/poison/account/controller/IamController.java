package org.poison.account.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.IamClient;
import org.poison.account.core.req.LoginRequest;
import org.poison.account.core.req.LogoutRequest;
import org.poison.account.core.resp.LoginResponse;
import org.poison.account.service.IamService;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IamController implements IamClient {

    @Resource
    private IamService loginService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }


    @Override
    public void logout(LogoutRequest logoutRequest) {
         loginService.logout(logoutRequest);
    }
}
