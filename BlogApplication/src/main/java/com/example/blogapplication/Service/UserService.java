package com.example.blogapplication.Service;

import com.example.blogapplication.Api.ApiExceptions;
import com.example.blogapplication.Model.User;
import com.example.blogapplication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        user.setRegistration_date(LocalDateTime.now());
        userRepository.save(user);
    }

    public String updateUser(Integer id , User user){
        User u = userRepository.getById(id);
        if(u == null) throw new ApiExceptions("User not found");
        user.setUser_id(id);
        user.setRegistration_date(u.getRegistration_date());
        userRepository.save(user);
        return "User updated";
    }

    public String deleteUser(Integer id){
        User u = userRepository.getById(id);
        if(u == null) throw new ApiExceptions("User not found");
        userRepository.delete(u);
        return "User deleted";
    }

    public List<User> usersAfter(LocalDateTime localDateTime){
        return userRepository.bringUserAfterDate(localDateTime);
    }

    public User userByEmail(String email){
        User user = userRepository.bringUserbyEmail(email);
        if(user == null) throw  new ApiExceptions("User not found");
        return user;
    }

}
