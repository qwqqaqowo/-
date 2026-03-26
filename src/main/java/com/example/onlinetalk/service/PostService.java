package com.example.onlinetalk.service;

import com.example.onlinetalk.entity.Comment;
import com.example.onlinetalk.entity.Post;
import com.example.onlinetalk.mapper.CommentMapper;
import com.example.onlinetalk.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CommentMapper commentMapper;

    public List<Post> listAllPosts() {
        return postMapper.listAllPosts();
    }

    public void addPost(Post post) {
        postMapper.addPost(post);
    }

    public Post getPostById(Integer id) {
        return postMapper.getPostById(id);
    }

    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentMapper.getCommentsByPostId(postId);
    }
}