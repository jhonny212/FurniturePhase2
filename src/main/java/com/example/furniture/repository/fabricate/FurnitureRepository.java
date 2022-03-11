package com.example.furniture.repository.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture,Integer> {
    
}
