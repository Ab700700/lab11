package com.example.blogapplication.Repository;

import com.example.blogapplication.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select post from Post post where post.category_id =?1")
    List<Post> bringPostsByCategory(Integer id);
    @Query("select post from Post post where post.post_id =?1")
    Post findPostByPost_id(Integer post_id);
    @Query("select post from Post post where post.author_id =?1")
    List<Post> findPostsByAuthor_id(Integer author_id);


}
