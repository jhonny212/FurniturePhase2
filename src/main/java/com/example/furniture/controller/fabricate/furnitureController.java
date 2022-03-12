package com.example.furniture.controller.fabricate;

import com.example.furniture.model.Furniture;
import com.example.furniture.service.fabricate.FurnitureService;
import com.example.furniture.serviceImp.fabricate.FurnitureServiceImp;
import com.example.furniture.util.Utility;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/fabricate/furniture")
public class furnitureController {

    @Autowired
    private FurnitureServiceImp furnitureServiceImp;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private Utility utilityService;

    @PostMapping("/register-furniture")
    public Furniture registerFurniture(@RequestBody Furniture furniture, @RequestParam("name") String dad){

//        System.out.println(multiPart);
        System.out.println(dad);
//        System.out.println(multiPart.toString());
//        utilityService.saveFile(multiPart,"../resources/img/");
        return furniture;
//        return this.furnitureServiceImp.postFurniture(furniture);
    }

    @GetMapping("/get-furniture")
    public String getFurniture(
            @RequestParam Optional<String> filter
    ){
        System.out.println("Dio");
        return furnitureServiceImp.getFurniture(filter.get());
    }

}
