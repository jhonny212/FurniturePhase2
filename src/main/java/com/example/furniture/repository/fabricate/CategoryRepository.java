package com.example.furniture.repository.fabricate;
import com.example.furniture.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
    List<Category> findAllByNameContains(String name);
}
