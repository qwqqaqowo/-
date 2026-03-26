package com.example.onlinetalk.service;

import com.example.onlinetalk.entity.Comment;
import com.example.onlinetalk.entity.Post;
import com.example.onlinetalk.mapper.CommentMapper;
import com.example.onlinetalk.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public PostService(PostMapper postMapper, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    // ====================== 分页 + 搜索（唯一正确） ======================
    public List<Post> listByPage(String keyword, Integer pageNum, Integer pageSize) {
        int pageStart = (pageNum - 1) * pageSize;
        return postMapper.listByPage(keyword, pageStart, pageSize);
    }

    public int count(String keyword) {
        return postMapper.count(keyword);
    }
    // ===================================================================

    public void addPost(Post post) {
        postMapper.addPost(post);
    }

    public Post getPostById(Integer id) {
        return postMapper.getPostById(id);
    }

    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentMapper.getCommentsByPostId(postId);
    }

    public void deleteById(Integer id) {
        postMapper.deleteById(id);
    }

    public Integer getUserIdByPostId(Integer id) {
        return postMapper.getUserIdByPostId(id);
    }
}