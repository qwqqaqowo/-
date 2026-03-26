package com.example.onlinetalk.mapper;

import com.example.onlinetalk.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {
    @Insert("insert into comment(content,post_id,user_id,create_time) values(#{content},#{postId},#{userId},#{createTime})")
    int addComment(Comment comment);

    @Select("select * from comment where post_id=#{postId} order by create_time asc")
    List<Comment> getCommentsByPostId(Integer postId);
}