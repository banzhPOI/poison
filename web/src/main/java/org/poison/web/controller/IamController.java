package org.poison.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.IamClient;
import org.poison.account.core.req.LoginRequest;
import org.poison.account.core.resp.LoginResponse;
import org.poison.order.core.req.OrderOperateRequest;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("iam")
public class IamController {

    @Resource
    private IamClient iamClient;


    @PostMapping(value = "login")
    public void create(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = iamClient.doLogin(loginRequest);
        response.setHeader(loginResponse.getTokenKey(),loginResponse.getTokenValue());
    }
}
