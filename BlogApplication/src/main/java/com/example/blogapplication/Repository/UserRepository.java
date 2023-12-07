package com.example.blogapplication.Repository;

import com.example.blogapplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email =?1")
    User bringUserbyEmail(String email);

    @Query("select u from User u where u.registration_date> ?1")
    List<User> bringUserAfterDate(LocalDateTime dateTime);

}
