package com.example.blogapplication.Service;

import com.example.blogapplication.Api.ApiExceptions;
import com.example.blogapplication.Model.Category;
import com.example.blogapplication.Model.Post;
import com.example.blogapplication.Model.User;
import com.example.blogapplication.Repository.CategoryRepository;
import com.example.blogapplication.Repository.PostRepository;
import com.example.blogapplication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private  final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        User user = userRepository.getById(post.getAuthor_id());
        Category category = categoryRepository.bringCategoryBeId(post.getCategory_id());
        if(user == null || category == null) throw new ApiExceptions("Author or category not found");
        post.setPublication_date(LocalDateTime.now());
        postRepository.save(post);
    }

    public String updatePost(Integer userid, Integer postid,Post post){
        User user = userRepository.getById(userid);
        Post p  = postRepository.getById(postid);
        User postuser = userRepository.getById(p.getAuthor_id());
        Category category = categoryRepository.bringCategoryBeId(post.getCategory_id());
        if(user == null || p == null|| category == null) throw new ApiExceptions("User, post or category not found");
        else if(!user.equals(postuser)) throw new ApiExceptions("User dose not have a permission to update post");
        else{
            post.setPost_id(postid);
            // Here I can update the date but it doesn't make sense for me :)
            post.setPublication_date(p.getPublication_date());
            postRepository.save(post);
            return "Post updated";
        }
    }

    public String deletePost(Integer userid,Integer postid){
        Post post = postRepository.getById(postid);
        User user = userRepository.getById(userid);
        User author = userRepository.getById(post.getAuthor_id());
        if(post == null || user == null) throw new ApiExceptions("User or post not found");
        else if(!user.equals(author)) throw  new ApiExceptions("User dose not have a permission to delete the post");
        else{
            postRepository.delete(post);
            return "Post deleted";
        }
    }

    public List<Post> findPostsByAuthId(Integer id){
        return postRepository.findPostsByAuthor_id(id);
    }
    public Post searchPost(Integer id){
        Post post = postRepository.findPostByPost_id(id);
        if(post == null) throw  new ApiExceptions("Post not found");
        return post;
    }


    public List<Post> findPostsByCategory(String name){
        Category category = categoryRepository.bringCategoryByName(name);

        return postRepository.bringPostsByCategory(category.getCategory_id());
    }
}
