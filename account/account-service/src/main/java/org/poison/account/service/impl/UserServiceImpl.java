package org.poison.account.service.impl;

import jakarta.annotation.Resource;
import org.poison.account.core.req.UserRegisterRequest;
import org.poison.account.mapper.UserMapper;
import org.poison.account.pojo.dto.UserDTO;
import org.poison.account.pojo.entity.User;
import org.poison.account.service.UserService;
import org.poison.common.exception.BizException;
import org.springframework.dao.DataIntegrityViolationException;
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
        // 静态校验
        request.checkParam();
        try {
            userMapper.register(User.fromRegisterRequest(request));
        } catch (DataIntegrityViolationException e) {
            throw new BizException("手机号或邮箱已被注册");
        }
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
