package com.example.blogapplication.Service;

import com.example.blogapplication.Api.ApiExceptions;
import com.example.blogapplication.Model.Category;
import com.example.blogapplication.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategoies(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public String updateCategory(Integer id, Category category){
        Category oldCategory = categoryRepository.getById(id);
        if(oldCategory == null) throw  new ApiExceptions("Category not found");
        category.setCategory_id(id);
        categoryRepository.save(category);
        return "Category updated";
    }

    public String deleteCategory(Integer id){
        Category category = categoryRepository.getById(id);
        if(category == null) throw new ApiExceptions("Category not found");
        categoryRepository.delete(category);
        return "Category deleted";
    }
}
