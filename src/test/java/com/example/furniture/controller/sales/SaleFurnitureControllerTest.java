package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Furniture;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.serviceImp.fabricate.FurnitureServiceImp;
import com.example.furniture.serviceImp.sales.SaleFurnitureServiceImp;
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

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SaleFurnitureController.class)
class SaleFurnitureControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    String token;
    String URL = "/sale/furniture";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleFurnitureServiceImp saleFurnitureServiceImp;
    @MockBean
    private FurnitureServiceImp furnitureServiceImp;
    @MockBean
    private BillDetailsRepository billDetailsRepository;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setUsername("s");
        profile.setUserType(0);
        profile.setId(5);
        token = jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId());
    }

    @Test
    void refundFurniture() throws Exception{
        String Dateinicio     = "2022-03-28";
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio      = date.parse(Dateinicio);
        HashMap<String,Object> responses = new HashMap<>();
        responses.put("status_code",400);
        responses.put("msj","Error al realizar devolucion, intente de nuevo");
        String output = this.mapToJson(responses);
        Mockito.when(billDetailsRepository.getDetail(
                Mockito.any(Integer.class),
                Mockito.any(Integer.class))
        ).thenReturn(fechaInicio);
        Mockito.when(furnitureServiceImp.getFurniture(
                Mockito.any(Integer.class))
        ).thenReturn(new Furniture());
        Mockito.when(saleFurnitureServiceImp.repaymentFurniture(
                Mockito.any(Furniture.class),
                Mockito.any(Integer.class),
                Mockito.any(Date.class))
        ).thenReturn(true);
        //Mockito.when(validationService.validate(Mockito.any(Category.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/devolution/1/bill/1")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), MediaType.APPLICATION_JSON.toString());
        assertEquals(response.getContentAsString(),output);
    }

    @Test
    void getDetailBill() throws Exception{
        List<Object[]> obj = new ArrayList<>();
        HashMap<String,Object> responses = new HashMap<>();
        responses.put("status_code",400);
        responses.put("msj","Error al realizar devolucion, intente de nuevo");
        String output = this.mapToJson(responses);
        Mockito.when(saleFurnitureServiceImp.getAllDetailsByBillId(
                Mockito.any(Integer.class)
                )
        ).thenReturn(obj);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/devolution/1/bill/1")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void getDetailClientByBillId() throws Exception{
        Object obj = new Object();

        Mockito.when(saleFurnitureServiceImp.getClientByIdBill(
                        Mockito.any(Integer.class)
                )
        ).thenReturn(obj);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/client/1")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), MediaType.APPLICATION_JSON.toString());
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