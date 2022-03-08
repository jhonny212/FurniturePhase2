package com.example.furniture.controller.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.serviceImp.fabricate.CategoryServiceImp;
import com.example.furniture.util.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class categoryController {
    
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private ValidationService validationService;

    @PostMapping("/create-category")
    public Category createCategory(@RequestBody Category category){
        boolean bol = validationService.validate(category);
        if (bol) {
            return this.categoryServiceImp.createCategory(category);
        }
        category.msj="Complete todos los campos"; 
        return category;
    }

}
