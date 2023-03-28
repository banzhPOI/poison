package org.poison.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.UserClient;
import org.poison.account.core.req.RegisterRequest;
import org.poison.account.core.resp.LoginResponse;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController implements UserClient {

    @Override
    public LoginResponse login(RegisterRequest registerRequest) {
        return null;
    }
}
