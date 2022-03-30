package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.BillDetails;
import com.example.furniture.model.Profile;
import com.example.furniture.serviceImp.sales.InvoiceServiceImp;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InvoiceController.class)
class InvoiceControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    String token;
    String URL = "/sales/invoice";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceServiceImp invoiceServiceImp;
    @MockBean
    private SaleFurnitureServiceImp saleFurnitureServiceImp;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setUsername("s");
        profile.setUserType(0);
        profile.setId(5);
        token = jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId());
    }

    @Test
    void getBillClient() throws Exception{
        Mockito.when(invoiceServiceImp.getBillsClient(
                Mockito.any(Optional.class),
                Mockito.any(Optional.class))
        ).thenReturn(null);
        //Mockito.when(validationService.validate(Mockito.any(Category.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-bill-cliente")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), null);
    }

    @Test
    void getReturnClient() throws Exception{
        Mockito.when(invoiceServiceImp.getReturnClient(
                Mockito.any(Optional.class),
                Mockito.any(Optional.class),
                Mockito.any(Optional.class),
                Mockito.any(Optional.class))
                ).thenReturn(null);
        //Mockito.when(validationService.validate(Mockito.any(Category.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-return-client")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), null);
    }

    @Test
    void getSalesToday() throws Exception{
        Mockito.when(invoiceServiceImp.getSalesToday(
                Mockito.any(Optional.class),
                Mockito.any(Optional.class))
        ).thenReturn(null);
        //Mockito.when(validationService.validate(Mockito.any(Category.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-sale-today")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), null);
    }

    @Test
    void getSalesClient() throws Exception{
        Mockito.when(invoiceServiceImp.getBills(
                Mockito.any(Optional.class),
                Mockito.any(Optional.class),
                Mockito.any(Optional.class),
                Mockito.any(Optional.class))
        ).thenReturn(null);
        //Mockito.when(validationService.validate(Mockito.any(Category.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-sales-clients")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), null);
    }

    @Test
    void testGetSalesClient() {
    }

    @Test
    void getEarningsTotal() throws Exception{
        Mockito.when(saleFurnitureServiceImp.getEarningsTotal(
                Mockito.any(Optional.class),
                Mockito.any(Optional.class))
        ).thenReturn(null);
        //Mockito.when(validationService.validate(Mockito.any(Category.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-earnings-total")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(), null);
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