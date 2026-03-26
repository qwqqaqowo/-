package com.example.onlinetalk.mapper;

import com.example.onlinetalk.entity.Post;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface PostMapper {

    @Select("SELECT p.*, u.nickname " +
            "FROM post p " +
            "LEFT JOIN user u ON p.user_id = u.id " +
            "WHERE title LIKE CONCAT('%',#{keyword},'%') " +
            "ORDER BY p.create_time DESC " +
            "LIMIT #{pageStart},#{pageSize}")
    List<Post> listByPage(String keyword, Integer pageStart, Integer pageSize);

    @Select("SELECT COUNT(*) FROM post WHERE title LIKE CONCAT('%',#{keyword},'%')")
    int count(String keyword);


    @Select("INSERT INTO post(title,content,user_id,create_time) VALUES(#{title},#{content},#{userId},#{createTime})")
    int addPost(Post post);

    @Select("SELECT p.*, u.nickname FROM post p LEFT JOIN user u ON p.user_id = u.id WHERE p.id=#{id}")
    Post getPostById(Integer id);

    @Select("DELETE FROM post WHERE id=#{id}")
    void deleteById(Integer id);

    @Select("SELECT user_id FROM post WHERE id=#{id}")
    Integer getUserIdByPostId(Integer id);
}