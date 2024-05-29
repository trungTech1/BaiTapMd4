package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.interfaces.ICategoryDao;
import com.example.demo.interfaces.ICategoryService;
import com.example.demo.model.Category;

import java.util.List;

public class CategoryService implements ICategoryService {
    private static final ICategoryDao Category_Dao = new CategoryDao();
    @Override
    public List<Category> getAllCategory() {
       return Category_Dao.getAllCategories();
    }

    @Override
    public void saveCategory(Category category) {
        Category_Dao.saveCategory(category);

    }

    @Override
    public void deleteCategory(Integer id) {
        Category_Dao.deleteCategory(id);
    }
}
