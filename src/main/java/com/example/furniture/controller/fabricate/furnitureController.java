package com.example.furniture.controller.fabricate;

import com.example.furniture.service.fabricate.FurnitureService;
import com.example.furniture.serviceImp.fabricate.FurnitureServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/furniture")
public class furnitureController {

    @Autowired
    private FurnitureServiceImp furnitureServiceImp;

    @Autowired
    private ValidationService validationService;


    private void hola (){
//        furnitureServiceImp.
    }

}
