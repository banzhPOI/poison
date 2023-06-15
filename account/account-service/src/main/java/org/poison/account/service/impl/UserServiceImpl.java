package org.poison.account.service.impl;

import jakarta.annotation.Resource;
import org.poison.account.core.req.UserRegisterRequest;
import org.poison.account.core.resp.UserVO;
import org.poison.account.mapper.UserMapper;
import org.poison.account.pojo.dto.UserDTO;
import org.poison.account.pojo.entity.User;
import org.poison.account.service.UserService;
import org.poison.common.exception.BizException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void register(UserRegisterRequest request) {
        if (request == null) {
            throw new BizException("注册请求为空");
        }
        request.checkParam();
        userMapper.register(User.fromRegisterRequest(request));
    }

    @Override
    public UserDTO checkUser(String loginKey, String password) {
        return UserDTO.fromEntity(userMapper.checkUser(loginKey, password));
    }

    @Override
    public UserDTO findUserById(String userId) {
        return UserDTO.fromEntity(userMapper.findById(userId));
    }
}
