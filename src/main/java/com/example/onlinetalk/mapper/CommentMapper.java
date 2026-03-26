package com.example.onlinetalk.mapper;

import com.example.onlinetalk.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommentMapper {

    // 新增评论
    @Insert("insert into comment(content,post_id,user_id,create_time) values(#{content},#{postId},#{userId},#{createTime})")
    int addComment(Comment comment);

    // 查询评论（带昵称）
    @Select("SELECT c.*, u.nickname " +
            "FROM comment c " +
            "LEFT JOIN user u ON c.user_id = u.id " +
            "WHERE c.post_id = #{postId} " +
            "ORDER BY c.create_time ASC")
    List<Comment> getCommentsByPostId(Integer postId);

    // 删除评论
    @Delete("delete from comment where id=#{id}")
    void deleteById(Integer id);

    // 根据评论ID查用户ID
    @Select("select user_id from comment where id=#{id}")
    Integer getUserIdByCommentId(Integer id);
}