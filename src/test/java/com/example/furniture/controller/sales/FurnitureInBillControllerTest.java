package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.model.Profile;
import com.example.furniture.serviceImp.fabricate.FurnitureInBillServiceImp;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FurnitureInBillController.class)
class FurnitureInBillControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    String token;
    String URL = "/sales/furniture-in-bill";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FurnitureInBillServiceImp furnitureInBillService;
    @MockBean
    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setUsername("s");
        profile.setUserType(0);
        profile.setId(5);
        token = jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFurnituresInBill() throws Exception{
        List<FurnitureInBill> list = new ArrayList<>();
        HashMap<String, Object> responses = new HashMap<>();
        responses.put("furnituresInBill",list);
        String output = this.mapToJson(responses);
        Mockito.when(furnitureInBillService.getFurnituresInBillBySession(Mockito.anyInt())).thenReturn(list);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/on-session")
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentAsString(),output);
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void removeFurnitureFromBill() throws Exception{
        HashMap<String, Object> responses = new HashMap<>();
        responses.put("wasDeleted",true);
        String output = this.mapToJson(responses);
        Mockito.when(furnitureInBillService.removeFurnitureFromBill(Mockito.anyInt())).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL+"/on-session/1")
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentAsString(),output);
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void addFurnitureToBill() throws Exception{
        HashMap<String, Object> responses = new HashMap<>();
        responses.put("wasAdded",true);
        String output = this.mapToJson(responses);
        Mockito.when(validationService.validate(Mockito.any(FurnitureInBill.class))).thenReturn(true);
        Mockito.when(furnitureInBillService.addFurnitureToBill(Mockito.any(FurnitureInBill.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/on-session")
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON)
                .queryParam("code","1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentAsString(),output);
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
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