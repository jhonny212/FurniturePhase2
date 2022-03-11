package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.service.fabricate.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FurnitureServiceImp implements FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

}
