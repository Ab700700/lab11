package com.example.blogapplication.Repository;

import com.example.blogapplication.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query("select c from Comment c where c.author_id =?1")
    List<Comment> bringUserComments(Integer author_id);

    //List<Comment> findCommentsByPost_id(Integer post_id);
    @Query("select comment from Comment  comment where comment.post_id =?1 ")
    List<Comment> bringPostComments(Integer post_id);
}
