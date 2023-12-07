package com.example.blogapplication.Repository;

import com.example.blogapplication.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


    @Query("select ca from Category ca where ca.category_id =?1")
    Category bringCategoryBeId(Integer id);
    @Query("select category from Category category where category.category_name =?1")
    Category bringCategoryByName(String name);
}
