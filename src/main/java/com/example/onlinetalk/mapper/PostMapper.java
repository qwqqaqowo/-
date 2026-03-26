package com.example.onlinetalk.mapper;

import com.example.onlinetalk.entity.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {
    @Select("select * from post order by create_time desc")
    List<Post> listAllPosts();

    @Insert("insert into post(title,content,user_id,create_time) values(#{title},#{content},#{userId},#{createTime})")
    int addPost(Post post);

    @Select("select * from post where id=#{id}")
    Post getPostById(Integer id);
}