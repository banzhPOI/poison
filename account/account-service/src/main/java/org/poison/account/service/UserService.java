package org.poison.account.service;

import org.poison.account.pojo.dto.UserDTO;

public interface UserService {

    UserDTO findUserById(String userId);

    UserDTO findUserByUsername(String username);

    boolean checkUser(String username,String password);
}
