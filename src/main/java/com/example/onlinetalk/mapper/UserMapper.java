package com.example.onlinetalk.mapper;

import com.example.onlinetalk.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(String username, String password);

    @Insert("insert into user(username,password,nickname,create_time) values(#{username},#{password},#{nickname},#{createTime})")
    int register(User user);

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);
}