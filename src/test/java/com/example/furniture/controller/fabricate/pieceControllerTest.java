package com.example.furniture.controller.fabricate;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Piece;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.admin.AssignPlanPieceRepository;
import com.example.furniture.serviceImp.fabricate.PieceServiceImp;
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
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = pieceController.class)
class pieceControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    String token;
    String URL = "/fabricate/piece";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PieceServiceImp pieceServiceImp;
    @MockBean
    private ValidationService validationService;
    @MockBean
    private AssignPlanPieceRepository assignPlanPieceRepository;
    Piece piece;
    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setUsername("s");
        profile.setUserType(0);
        profile.setId(5);
        token = jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId());
        piece = new Piece();
        piece.setName("Pieza");
        piece.setPrice(-1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPiece() throws Exception{
        piece.msj = "Ingrese un costo o precio mayor a 0";
        String input = this.mapToJson(piece);
        Mockito.when(pieceServiceImp.createPiece(Mockito.any(Piece.class))).thenReturn(piece);
        Mockito.when(validationService.validate(Mockito.any(Piece.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/register-piece")
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON).content(input)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
        assertEquals(response.getContentAsString(),input);
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void updatePiece() throws Exception{
        piece.setPrice(10);
        piece.setId(1);
        String input = this.mapToJson(piece);
        Mockito.when(validationService.validate(Mockito.any(Piece.class))).thenReturn(true);
        Mockito.when(pieceServiceImp.getPieceById(Mockito.any(Integer.class))).thenReturn(piece);
        Mockito.when(pieceServiceImp.updatePiece(Mockito.any(Piece.class))).thenReturn(piece);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL+"/update-piece")
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
    void getPieceById() throws Exception {
        piece.setId(1);
        String input = this.mapToJson(piece);
        Mockito.when(pieceServiceImp.getPieceById(Mockito.any(Integer.class))).thenReturn(piece);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-piece/1")
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON).content(input)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentAsString(),input);
        assertEquals(response.getContentType(),MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void getAllPieces() throws Exception{
        Mockito.when(pieceServiceImp.getAllPieces(
                Mockito.any(Optional.class),
                Mockito.any(Optional.class)
        )).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/get-all")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentType(),null);
    }

    @Test
    void deletePiece() throws Exception{
        HashMap<String,String> responses = new HashMap<>();
        responses.put("msj","Se ha eliminado la pieza con éxito");
        String input = this.mapToJson(responses);
        Mockito.when(pieceServiceImp.deletePiece(Mockito.any(Integer.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL+"/delete/1")
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON).content(input)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(response.getContentAsString(),input);
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