package com.example.furniture.service.sales;

import com.example.furniture.model.Furniture;

import java.util.List;

public interface Bill {

    public boolean doBill(List<Furniture> furniture);
}
