package com.example.blogapplication.Controller;

import com.example.blogapplication.Model.Comment;
import com.example.blogapplication.Service.CommetnService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommetnService commentService;

    @GetMapping("/get")
    public ResponseEntity getComments(){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        commentService.addComment(comment);
        return ResponseEntity.status(HttpStatus.OK).body("Comment added");
    }

    @PutMapping("/update/{id}/{userid}")
    public ResponseEntity updateComment(@PathVariable Integer id , @PathVariable Integer userid, @RequestBody @Valid Comment comment,Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(id,userid,comment));

    }

    @DeleteMapping("/delete/{userid}/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer userid, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(userid,id));
    }

    @GetMapping("/usercomments/{userid}")
    public ResponseEntity userComments(@PathVariable Integer userid){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.userComments(userid));
    }

    @GetMapping("/postcomments/{postid}")
    public ResponseEntity postComments(@PathVariable Integer postid){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.postComments(postid));
    }
}
