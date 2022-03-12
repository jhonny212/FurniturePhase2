package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Furniture;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.service.fabricate.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class FurnitureServiceImp implements FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Override
    public String getFurniture(String filter){


        return "hOLA";
    }

    @Override
    public Furniture postFurniture(Furniture furniture){
        try{

            return this.furnitureRepository.save(furniture);
        }catch (DataIntegrityViolationException e){
//            furniture.msj = "";
            return furniture;
        }

    }

}
