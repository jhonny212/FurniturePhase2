package com.example.furniture.controller;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.user.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
class UserControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    String token;
    String URL = "/user";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setUsername("s");
        profile.setUserType(0);
        profile.setId(5);
        token = jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId());
    }

    @Test
    void login() throws Exception{
        HashMap<String, String> responses = new HashMap<>();
        responses.put("msj","No existe el usuario ingresado en el sistema");
        String output = this.mapToJson(responses);
        Mockito.when(userRepository.findByUsername(
                Mockito.any(String.class)
                )
        ).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/login")
                .header("Authorization",token)
                .queryParam("user","ss")
                .queryParam("password","s")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), MediaType.APPLICATION_JSON.toString());
        assertEquals(response.getContentAsString(),output);
    }

    @Test
    void isAdminLoggedIn() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/isAdminLoggedIn")
                .header("Authorization",token)
               .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), MediaType.APPLICATION_JSON.toString());
        assertEquals(response.getContentAsString(),"true");
    }

    @Test
    void isSalesmanLoggedIn() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/isSalesmanLoggedIn")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), MediaType.APPLICATION_JSON.toString());
        assertEquals(response.getContentAsString(),"false");
    }

    @Test
    void isFabricatemanLoggedIn() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/isFabricatemanLoggedIn")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), MediaType.APPLICATION_JSON.toString());
        assertEquals(response.getContentAsString(),"false");
    }

    @Test
    void isLoggedIn() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/isLoggedIn")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), MediaType.APPLICATION_JSON.toString());
        assertEquals(response.getContentAsString(),"true");
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