package com.example.blogapplication.Controller;

import com.example.blogapplication.Model.Post;
import com.example.blogapplication.Repository.PostRepository;
import com.example.blogapplication.Service.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog/post")
@RequiredArgsConstructor
public class PostController {

    private  final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        postService.addPost(post);
        return ResponseEntity.status(HttpStatus.OK).body("Post added");
    }

    @PutMapping("/update/{userid}/{id}")
    public ResponseEntity updatePost(@RequestBody @Valid Post post, @PathVariable Integer userid , @PathVariable Integer id , Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(userid,id,post));
    }

    @DeleteMapping("/delete/{userid}/{id}")
    public ResponseEntity deletePost(@PathVariable Integer userid, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(userid,id));
    }

    @GetMapping("/findpost/{id}")
    public ResponseEntity search(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.searchPost(id));
    }

    @GetMapping("/userpost/{userid}")
    public ResponseEntity searchPostByUserId(@PathVariable Integer userid){
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPostsByAuthId(userid));
    }

    @GetMapping("/categoryposts/{category}")
    public ResponseEntity postsByCategory(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPostsByCategory(category));
    }

}
