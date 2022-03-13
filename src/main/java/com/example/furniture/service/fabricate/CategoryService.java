package com.example.furniture.service.fabricate;

import java.util.List;

import com.example.furniture.model.Category;

public interface CategoryService {
    
    public Category createCategory(Category category);
    public List<Category> getAllCategories();
    public List<Category> getAllCategories(String filter);
    public boolean deleteCategory(Integer id);
}
