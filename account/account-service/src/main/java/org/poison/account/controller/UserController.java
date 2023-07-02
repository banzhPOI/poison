package org.poison.account.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.client.UserClient;
import org.poison.account.core.req.UserGetRequest;
import org.poison.account.core.req.UserRegisterRequest;
import org.poison.account.core.resp.UserVO;
import org.poison.account.pojo.dto.UserDTO;
import org.poison.account.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController implements UserClient {

    @Resource
    private UserService userService;

    @Override
    public void register(UserRegisterRequest request) {
        userService.register(request);
    }

    @Override
    public List<UserVO> getAllUserList() {
        return UserDTO.toVO(userService.getAllUserList());
    }

    @Override
    public UserVO findUserById(UserGetRequest request) {
        return UserDTO.toVO(userService.findUserById(request.getUserId()));
    }
}
