package org.poison.account.client;

import org.poison.account.core.req.LoginRequest;
import org.poison.account.core.req.LogoutRequest;
import org.poison.account.core.resp.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banzh-account-service", contextId = "iam")
public interface IamClient {

    @PostMapping("iam/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest);

    @PostMapping("iam/logout")
    void logout(@RequestBody LogoutRequest logoutRequest);

}
