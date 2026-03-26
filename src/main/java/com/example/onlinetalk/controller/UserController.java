package com.example.onlinetalk.controller;

import com.example.onlinetalk.entity.User;
import com.example.onlinetalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String toLogin() {
        return "login"; // 跳转到登录页
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/post/list"; // 登录成功跳帖子列表
        }
        return "login"; // 登录失败返回登录页
    }

    @GetMapping("/register")
    public String toRegister() {
        return "register"; // 跳转到注册页
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.register(user);
        return "redirect:/user/login"; // 注册成功跳登录页
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login"; // 退出登录跳登录页
    }
}