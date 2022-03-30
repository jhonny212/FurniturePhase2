package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.repository.fabricate.CategoryRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CategoryServiceImpTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImp categoryServiceImp;
    Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.category = new Category("categoria");
    }

    @Test
    void createCategory() {
        Mockito.when(
            categoryRepository.save(ArgumentMatchers.any(Category.class))
        ).thenReturn(category);
        assertNotNull(categoryServiceImp.createCategory(category));
    }

    @Test
    void getAllCategories() {
        List<Category> categoryList  = new ArrayList<>();
        Mockito.when(
                categoryRepository.findAll()
        ).thenReturn(categoryList);
        assertNotNull(categoryServiceImp.getAllCategories());
        assertEquals(categoryServiceImp.getAllCategories(),categoryList);
    }

    @Test
    void testGetAllCategories() {
        List<Category> categoryList  = new ArrayList<>();
        Mockito.when(
                categoryRepository.findAllByNameContains(Mockito.any(String.class))
        ).thenReturn(categoryList);
        assertNotNull(categoryServiceImp.getAllCategories(""));
        assertEquals(categoryServiceImp.getAllCategories(""),categoryList);
    }

    @Test
    void deleteCategory() {
        List<Category> categoryList  = new ArrayList<>();
        Mockito.when(
                categoryRepository.existsById(Mockito.any(Integer.class))
        ).thenReturn(false);
        assertNotNull(categoryServiceImp.deleteCategory(1));
        assertEquals(categoryServiceImp.deleteCategory(1),false);
    }
}