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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 4, message = "Title should at least be 4 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;
    @NotEmpty(message = "Content should not be empty")
    @Size(min = 20 , message = "Content should at least be 20 characters")
    @Column(columnDefinition = "varchar(200) not null")
    private String content;
    @NotNull(message = "Author id should not be empty")
    @Positive(message = "Author id should be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer author_id;
    @NotNull(message = "Category id should not be empty")
    @Positive(message = "Category id should be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer category_id;
    private LocalDateTime publication_date;
}
