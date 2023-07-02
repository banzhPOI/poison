package org.poison.web.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.UserClient;
import org.poison.account.core.req.UserRegisterRequest;
import org.poison.account.core.resp.LoginResponse;
import org.poison.account.core.resp.UserVO;
import org.poison.common.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserClient userClient;

    @PostMapping(value = "register")
    public Response<LoginResponse> register(@RequestBody UserRegisterRequest request) {
        userClient.register(request);
        return Response.success();
    }

    @PostMapping(value = "list/all")
    public Response<List<UserVO>> getAllUserList() {
        return Response.success(userClient.getAllUserList());
    }
}
