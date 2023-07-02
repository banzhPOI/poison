package org.poison.account.client;

import org.poison.account.core.req.UserRegisterRequest;
import org.poison.account.core.req.UserGetRequest;
import org.poison.account.core.resp.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "poison-account-service", contextId = "user")
public interface UserClient {

    @PostMapping("user/register")
    void register(@RequestBody UserRegisterRequest request);

    @PostMapping("user/list/all")
    List<UserVO> getAllUserList();

    @PostMapping("user/get")
    UserVO findUserById(@RequestBody UserGetRequest request);
}
