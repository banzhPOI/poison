package org.poison.account.service.impl;

import org.poison.account.pojo.dto.UserDTO;
import org.poison.account.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final UserDTO user;

    static {
        user = new UserDTO();
        user.setId("1");
        user.setUsername("banzh");
        user.setPassword("123456");
    }

    @Override
    public UserDTO findUserById(String userId) {
        if (userId.equals(user.getId())) {
            return user;
        }
        return null;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        if (username.equals(user.getUsername())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean checkUser(String username, String password) {
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
}
