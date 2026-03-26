package com.example.onlinetalk.service;

import com.example.onlinetalk.entity.Comment;
import com.example.onlinetalk.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }

    public void deleteById(Integer id) {
        commentMapper.deleteById(id);
    }
    public Integer getUserIdByCommentId(Integer id) {
        return commentMapper.getUserIdByCommentId(id);
    }
}