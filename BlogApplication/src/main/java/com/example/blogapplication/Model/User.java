package com.example.blogapplication.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 4, message = "Username should be at least 4 characters")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String username;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Write a valid emial")
    @Size(min=7, message = "Email should at least be 7 characters")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 5, message = "Password should at least be 5 characters")
    @Column(columnDefinition = "varchar(50) not null")
    private String password;

    private LocalDateTime registration_date;
}
