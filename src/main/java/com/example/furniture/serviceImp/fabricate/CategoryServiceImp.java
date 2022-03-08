package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.repository.fabricate.CategoryRepository;
import com.example.furniture.service.fabricate.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return this.categoryRepository.save(category);
    }
    
}
