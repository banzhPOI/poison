package org.poison.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.poison.account.core.req.UserRegisterRequest;
import org.poison.account.pojo.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    void register(User user);

    User findByLoginKey(UserRegisterRequest request);

    List<User> getAll();

    User findById(@Param("id") String id);

    User checkUser(@Param("loginKey") String loginKey, @Param("password") String password);

}
