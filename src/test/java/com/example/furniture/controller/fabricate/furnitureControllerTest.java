package com.example.furniture.controller.fabricate;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Furniture;
import com.example.furniture.model.Plan;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.admin.AssignPlanPieceRepository;
import com.example.furniture.repository.fabricate.AssignFurniturePieceRepository;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.repository.fabricate.PieceRepository;
import com.example.furniture.repository.fabricate.StockPieceRepository;
import com.example.furniture.serviceImp.fabricate.FurnitureServiceImp;
import com.example.furniture.util.Utility;
import com.example.furniture.util.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = furnitureController.class)
class furnitureControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    String token;
    String URL = "/fabricate/furniture";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FurnitureServiceImp furnitureServiceImp;

    @MockBean
    private Utility utilityService;

    @MockBean
    private ValidationService validationService;

    private Furniture furniture;

    @MockBean
    AssignPlanPieceRepository assignPlanPieceRepository;
    @MockBean
    PieceRepository pieceRepository;
    @MockBean
    StockPieceRepository stockPieceRepository;
    @MockBean
    FurnitureRepository furnitureRepository;
    @MockBean
    AssignFurniturePieceRepository assignFurniturePieceRepository;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setUsername("s");
        profile.setUserType(0);
        profile.setId(5);
        token = jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId());
        this.furniture = new Furniture(1, "name",1, 1, new Date(), "description", "path",
                new Profile(1, null, null, null, null, null),
                new Plan(1, null, null, true), 0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerFurniture() throws  Exception{
    }

    @Test
    void getFurniture() throws Exception{
        Mockito.when(furnitureServiceImp.isExisteFurniture(Mockito.any(Integer.class))).thenReturn(true);
        Mockito.when(furnitureServiceImp.getFurniture(Mockito.any(Integer.class))).thenReturn(furniture);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-furniture?code=1")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void getAllFuniture() throws Exception{
        Page<Furniture> page = null;
        Mockito.when(furnitureServiceImp.getAllFurniture(Mockito.any(Optional.class))).thenReturn(page);
        //Mockito.when(furnitureServiceImp.getFurniture(Mockito.any(Integer.class))).thenReturn(furniture);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-allFurniture")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    void getAllFunitureFilter() throws Exception{
        Page<Furniture> page = null;
        Mockito.when(furnitureServiceImp.getAllFurnitureFilter(
                Mockito.any(Optional.class),
                Mockito.any(Optional.class),
                Mockito.any(Optional.class),
                Mockito.any(Optional.class)
        )).thenReturn(page);
        //Mockito.when(furnitureServiceImp.getFurniture(Mockito.any(Integer.class))).thenReturn(furniture);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-allFurniture")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    void putFurnitureOnSale() throws Exception{
        HashMap<String,String> responses= new HashMap<>();
        responses.put("msj","1");
        String output = mapToJson(responses);
        Mockito.when(furnitureServiceImp.updateFurniture(
                Mockito.any(Integer.class)
        )).thenReturn("1");
        //Mockito.when(furnitureServiceImp.getFurniture(Mockito.any(Integer.class))).thenReturn(furniture);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL+"/put-furniture-on-sale/1")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentAsString(),output);
    }

    @Test
    void getFurnituresOnSale() throws Exception{
        Page<Furniture> page = null;
        Mockito.when(furnitureServiceImp.getFurnituresOnSale(
                Mockito.any(Optional.class),
                Mockito.any(Optional.class)
        )).thenReturn(page);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/on-sale")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
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