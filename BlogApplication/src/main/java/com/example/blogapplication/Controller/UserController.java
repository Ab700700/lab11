package com.example.blogapplication.Controller;

import com.example.blogapplication.Model.User;
import com.example.blogapplication.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/blog/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@RequestBody @Valid User user,@PathVariable Integer id,Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @GetMapping("/userafter/{date}")
    public ResponseEntity userAfter(@PathVariable LocalDateTime date){
        return ResponseEntity.status(HttpStatus.OK).body(userService.usersAfter(date));
    }

    @GetMapping("/useremail/{email}")
    public ResponseEntity userByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(userService.userByEmail(email));
    }
}
