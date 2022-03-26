package com.example.furniture.controller.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.serviceImp.fabricate.CategoryServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fabricate/category")
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

    @GetMapping("/get-all-categories")
    public List<Category> getAllCategories(
            @RequestParam Optional<String> filter
    ){
        if (filter.isPresent()){
            return this.categoryServiceImp.getAllCategories(filter.get());
        }
        return this.categoryServiceImp.getAllCategories();
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String,String> deleteCategory(@PathVariable("id") Integer id){
        HashMap<String,String> response = new HashMap<>();
        if(this.categoryServiceImp.deleteCategory(id)){
            response.put("msj","Se ha eliminado con éxito.");
        }else{
            response.put("msj","Ha ocurrido un error por lo que no se ha eliminado la categoria");
        }
        return response;
    }

}
