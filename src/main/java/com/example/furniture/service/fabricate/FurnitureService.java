package com.example.furniture.service.fabricate;

import com.example.furniture.model.Furniture;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FurnitureService {

    public Furniture getFurniture(Integer id);
    public Furniture postFurniture(Furniture furniture);
    public Page<Furniture> getAllFurniture(Optional<Integer> page);
    public Page<Furniture> getAllFurniture(Optional<String> filter, Optional<Integer> page);
    public Page<Furniture> getAllFurnitureFilter(Optional<Date> date1, Optional<Date> date2, Optional<Integer> sort, Optional<Integer> page) throws ParseException;
    public String updateFurniture(Integer id);
    public List<Furniture> getFurnituresOnSession();
    public boolean removeFurnitureFromBil();
    public boolean addFurnitureToBill();
    public Optional<Furniture> getFurnitureById(Integer id);
}
