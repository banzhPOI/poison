package org.poison.web.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.UserClient;
import org.poison.account.core.req.RegisterRequest;
import org.poison.account.core.resp.LoginResponse;
import org.poison.common.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserClient userClient;

    @PostMapping(value = "register")
    public Response<LoginResponse> login(@RequestBody RegisterRequest request) {
        userClient.register(request);
        return Response.success();
    }
}