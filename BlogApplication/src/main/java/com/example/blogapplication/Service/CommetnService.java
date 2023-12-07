package com.example.blogapplication.Service;

import com.example.blogapplication.Api.ApiExceptions;
import com.example.blogapplication.Model.Comment;
import com.example.blogapplication.Model.Post;
import com.example.blogapplication.Model.User;
import com.example.blogapplication.Repository.CommentRepository;
import com.example.blogapplication.Repository.PostRepository;
import com.example.blogapplication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommetnService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        User user = userRepository.getById(comment.getAuthor_id());
        Post post = postRepository.getById(comment.getPost_id());
        if(user == null ) throw new ApiExceptions("User is not found");
        else if(post == null) throw  new ApiExceptions("Post not found");
        else{
            comment.setComment_date(LocalDateTime.now());
            commentRepository.save(comment);
        }

    }

    public String updateComment(Integer commentid,Integer userid,Comment comment){
        Comment oldComment = commentRepository.getById(commentid);
        if(oldComment == null) throw  new ApiExceptions("Comment not found");
        User user = userRepository.getById(userid);
        User author = userRepository.getById(oldComment.getAuthor_id());
        if(!user.equals(author)||user.getUser_id()!= comment.getAuthor_id()) throw  new ApiExceptions("User is not allowed to update comment");
        else {
            comment.setComment_id(commentid);
            comment.setAuthor_id(oldComment.getAuthor_id());
            comment.setPost_id(oldComment.getPost_id());
            // same here I can update date.
            comment.setComment_date(oldComment.getComment_date());
            commentRepository.save(comment);
            return "Comment updated";
        }

    }


    public String deleteComment(Integer userid, Integer commentid){
        User user = userRepository.getById(userid);
        Comment comment = commentRepository.getById(commentid);
        if(user == null || comment == null) throw new ApiExceptions("User or comment is not found");
        else if(user.getUser_id()!= comment.getAuthor_id()) throw  new ApiExceptions("User is not allowed to remove the comment");
        else{
            commentRepository.delete(comment);
            return "Comment deleted";
        }
    }

    public List<Comment> userComments(Integer userid){
        return commentRepository.bringUserComments(userid);
    }

    public List<Comment> postComments(Integer postid){
        return commentRepository.bringPostComments(postid);
    }




}
