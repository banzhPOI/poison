package org.poison.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.poison.account.pojo.entity.User;

@Mapper
public interface UserMapper {

    void register(User user);

    User findById(@Param("id") String id);

    User checkUser(@Param("loginKey") String loginKey, @Param("password") String password);
}
