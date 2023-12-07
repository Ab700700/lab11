package com.example.blogapplication.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;
    @NotEmpty(message = "Category name should not be empty")
    @Size(min = 4, message = "Category name should be at least 4 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String category_name;

}
