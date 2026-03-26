package com.example.onlinetalk.controller;

import com.example.onlinetalk.entity.Post;
import com.example.onlinetalk.entity.User;
import com.example.onlinetalk.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("posts", postService.listAllPosts());
        return "postList"; // 跳转到帖子列表页
    }

    @GetMapping("/add")
    public String toAdd() {
        return "addPost"; // 跳转到发帖页
    }

    @PostMapping("/add")
    public String addPost(Post post, HttpSession session) {
        User user = (User) session.getAttribute("user");
        post.setUserId(user.getId());
        post.setCreateTime(new Date());
        postService.addPost(post);
        return "redirect:/post/list"; // 发帖成功跳列表页
    }

    @GetMapping("/detail/{id}")
    public String detail(Integer id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("comments", postService.getCommentsByPostId(id));
        return "postDetail"; // 跳转到帖子详情页
    }
}