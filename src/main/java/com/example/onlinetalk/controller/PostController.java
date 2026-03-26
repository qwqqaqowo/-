package com.example.onlinetalk.controller;

import com.example.onlinetalk.entity.Post;
import com.example.onlinetalk.entity.User;
import com.example.onlinetalk.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // ====================== 【唯一正确的列表方法】 ======================
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {

        List<Post> posts = postService.listByPage(keyword, pageNum, pageSize);
        int total = postService.count(keyword);
        int pages = (total + pageSize - 1) / pageSize;

        model.addAttribute("posts", posts);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        return "postList";
    }


    @GetMapping("/add")
    public String toAdd() {
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(Post post, HttpSession session) {
        User user = (User) session.getAttribute("user");
        post.setUserId(user.getId());
        post.setCreateTime(new Date());
        postService.addPost(post);
        return "redirect:/post/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("comments", postService.getCommentsByPostId(id));
        return "postDetail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        Integer postUserId = postService.getUserIdByPostId(id);

        if (loginUser.getRole().equals("admin") || postUserId.equals(loginUser.getId())) {
            postService.deleteById(id);
        }
        return "redirect:/post/list";
    }
}