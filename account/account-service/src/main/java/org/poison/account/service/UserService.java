package org.poison.account.service;

import org.poison.account.core.req.UserRegisterRequest;
import org.poison.account.core.resp.UserVO;
import org.poison.account.pojo.dto.UserDTO;

import java.util.List;

public interface UserService {

    void register(UserRegisterRequest request);

    UserDTO checkUser(String loginKey, String password);

    List<UserDTO> getAllUserList();

    UserDTO findUserById(String userId);
}
