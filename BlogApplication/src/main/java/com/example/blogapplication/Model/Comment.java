package com.example.blogapplication.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;
    @NotNull(message = "Post id should not be empty")
    @Positive(message = "Post id should be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer post_id;
    @NotNull(message = "Author id should not be empty")
    @Positive(message = "Author id should be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer author_id;
    @NotEmpty(message = "Content should not be empty")
    @Size(min=20, message = "Content should at least be 20 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String content;
    private LocalDateTime comment_date;
}
