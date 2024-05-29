package com.example.demo.interfaces;

import com.example.demo.model.Book;
import com.example.demo.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
    void saveCategory(Category category);
    void deleteCategory(Integer id);
}
