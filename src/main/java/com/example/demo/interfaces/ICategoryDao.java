package com.example.demo.interfaces;

import com.example.demo.model.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    void saveCategory(Category category);
    Boolean deleteCategory(Integer id);
}
