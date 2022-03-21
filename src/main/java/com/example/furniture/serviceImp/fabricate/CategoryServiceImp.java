package com.example.furniture.serviceImp.fabricate;

import java.util.List;

        import com.example.furniture.model.Category;
        import com.example.furniture.repository.fabricate.CategoryRepository;
        import com.example.furniture.service.fabricate.CategoryService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.dao.DataIntegrityViolationException;
        import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        try {
            category.msj = "Categoria creada exitosamente";
            return this.categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            category.msj="La categoria ya existe";
            return category;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllCategories(String filter) {
        return this.categoryRepository.findAllByNameContains(filter);
    }

    @Override
    public boolean deleteCategory(Integer id){
        this.categoryRepository.deleteById(id);
        return this.categoryRepository.existsById(id);
    }

}
