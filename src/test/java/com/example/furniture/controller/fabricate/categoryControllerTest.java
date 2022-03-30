package com.example.furniture.controller.fabricate;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Category;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.user.UserRepository;
import com.example.furniture.serviceImp.fabricate.CategoryServiceImp;
import com.example.furniture.util.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = categoryController.class)
class categoryControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    String token;
    String URL = "/fabricate/category";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImp categoryServiceImp;

    @MockBean
    private ValidationService validationService;

    @Test
    void createCategory() throws Exception {
        String input = this.mapToJson(cat);
        Mockito.when(categoryServiceImp.createCategory(Mockito.any(Category.class))).thenReturn(cat);
        Mockito.when(validationService.validate(Mockito.any(Category.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/create-category")
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON).content(input)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        assertEquals(response.getContentAsString(),input);
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void getAllCategories() throws Exception{
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(cat);
        String input = mapToJson(categoryList);
        Mockito.when(categoryServiceImp.getAllCategories()).thenReturn(categoryList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-all-categories")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentAsString(),input);
    }

    @Test
    void deleteCategory() throws Exception{
        Mockito.when(categoryServiceImp.deleteCategory(Mockito.anyInt())).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL+"/delete/1")
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
    }

    private Category cat;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setUsername("s");
        profile.setUserType(0);
        profile.setId(5);
        token = jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId());
        cat = new Category("Hola");
    }

    @AfterEach
    void tearDown() {
    }

    private String mapToJson(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}