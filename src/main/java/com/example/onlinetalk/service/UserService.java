package com.example.onlinetalk.service;

import com.example.onlinetalk.entity.User;
import com.example.onlinetalk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    public void register(User user) {
        user.setCreateTime(new Date());
        if (user.getNickname() == null || user.getNickname().isEmpty()) {
            user.setNickname(user.getUsername());
        }
        userMapper.register(user);
    }
}