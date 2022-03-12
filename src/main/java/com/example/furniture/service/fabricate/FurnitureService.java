package com.example.furniture.service.fabricate;

import com.example.furniture.model.Furniture;

import java.util.List;

public interface FurnitureService {

    public String getFurniture(String filter);
    public Furniture postFurniture(Furniture furniture);
}
