package org.poison.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.IamClient;
import org.poison.account.core.req.LoginRequest;
import org.poison.account.core.req.LogoutRequest;
import org.poison.account.core.resp.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("iam")
public class IamController extends BaseController {

    @Resource
    private IamClient iamClient;


    @PostMapping(value = "login")
    public void login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = iamClient.login(loginRequest);
        response.setHeader(loginResponse.getTokenKey(), loginResponse.getTokenValue());
    }

    @PostMapping(value = "logout")
    public void logout() {
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUserId(getCurrentUserId());
        iamClient.logout(logoutRequest);
    }
}
