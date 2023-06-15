package org.poison.account.service;

import org.poison.account.core.req.UserRegisterRequest;
import org.poison.account.core.resp.UserVO;
import org.poison.account.pojo.dto.UserDTO;

public interface UserService {

    void register(UserRegisterRequest request);

    UserDTO checkUser(String loginKey, String password);

    UserDTO findUserById(String userId);
}
