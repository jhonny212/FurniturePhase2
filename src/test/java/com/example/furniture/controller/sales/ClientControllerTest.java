package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Client;
import com.example.furniture.model.Profile;
import com.example.furniture.serviceImp.sales.ClientServiceImp;
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

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientController.class)
class ClientControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    Client client;
    String token;
    String URL = "/sales/client";

    @MockBean
    private ClientServiceImp clientServiceImp;
    @MockBean
    private ValidationService validationService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setUsername("s");
        profile.setUserType(0);
        profile.setId(5);
        token = jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId());
        client = new Client();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getClient() throws Exception{
        HashMap<String, Object> responses = new HashMap<>();
        responses.put("found",false);
        String output = this.mapToJson(responses);
        Mockito.when(clientServiceImp.getClient(Mockito.any(Integer.class))).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/1")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentAsString(),output);
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void createClient() throws  Exception{
        HashMap<String, Object> responses = new HashMap<>();
        responses.put("wasAdded",true);
        String output = this.mapToJson(responses);
        String input = this.mapToJson(client);
        Mockito.when(validationService.validate(Mockito.any(Client.class))).thenReturn(true);
        Mockito.when(clientServiceImp.createClient(Mockito.any(Client.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL)
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON).content(input)
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