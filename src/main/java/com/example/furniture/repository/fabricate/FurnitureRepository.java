package com.example.furniture.repository.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.model.Furniture;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface FurnitureRepository extends JpaRepository<Furniture,Integer> {

    Page<Furniture> findByNameContains(String name, Pageable page);
    Page<Furniture> findByCreationDateBetween(Date date1, Date date2, Pageable page);
    Page<Furniture> findByStatus(Integer status, Pageable page);
}
