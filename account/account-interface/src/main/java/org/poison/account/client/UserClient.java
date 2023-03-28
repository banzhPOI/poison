package org.poison.account.client;

import org.poison.account.core.req.RegisterRequest;
import org.poison.account.core.resp.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banzh-account-service", contextId = "user")
public interface UserClient {

    @PostMapping("user/register")
    LoginResponse login(@RequestBody RegisterRequest registerRequest);
}
