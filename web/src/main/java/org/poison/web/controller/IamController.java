package org.poison.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.IamClient;
import org.poison.account.core.req.LoginRequest;
import org.poison.account.core.req.LogoutRequest;
import org.poison.account.core.resp.LoginResponse;
import org.poison.common.response.Response;
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
    public Response<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = iamClient.login(loginRequest);
        response.addCookie(new Cookie(loginResponse.getTokenKey(), loginResponse.getTokenValue()));
        return Response.success("登录成功", loginResponse);
    }

    @PostMapping(value = "logout")
    public Response<?> logout() {
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUserId(getCurrentUser().getId());
        iamClient.logout(logoutRequest);
        return Response.success("登出成功");
    }
}
