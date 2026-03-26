package com.example.onlinetalk.controller;

import com.example.onlinetalk.entity.Comment;
import com.example.onlinetalk.entity.User;
import com.example.onlinetalk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addComment(Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        comment.setUserId(user.getId());
        comment.setCreateTime(new Date());
        commentService.addComment(comment);
        return "redirect:/post/detail/" + comment.getPostId(); // 评论成功跳帖子详情
    }
}