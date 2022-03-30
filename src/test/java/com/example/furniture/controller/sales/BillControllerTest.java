package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.repository.sales.ClientRepository;
import com.example.furniture.repository.user.UserRepository;
import com.example.furniture.serviceImp.admin.BillServiceImp;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BillController.class)
class BillControllerTest {

    JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
    Profile profile;
    String token;
    String URL = "/sales/bill";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;
    @MockBean
    private ValidationService validationService;
    @MockBean
    private BillServiceImp billServiceImp;
    @MockBean
    private BillDetailsRepository billDetailsRepository;
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

    @AfterEach
    void tearDown() {
    }

    @Test
    void doBill() throws Exception{
        Bill bill = new Bill();
        Profile pf = new Profile();
        String input = this.mapToJson(bill);
        BillDetails billDetails = new BillDetails();
        Mockito.when(userRepository.findByUsername(Mockito.any(String.class))).thenReturn(pf);
        Mockito.when(clientRepository.existsById(Mockito.any(Integer.class))).thenReturn(true);
        Mockito.when(validationService.validate(Mockito.any(Bill.class))).thenReturn(true);
        Mockito.when(billServiceImp.doBill(Mockito.any(Bill.class))).thenReturn(true);
        Mockito.when(billDetailsRepository.save(Mockito.any(BillDetails.class))).thenReturn(billDetails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL)
                .header("Authorization",token)
                .accept(MediaType.APPLICATION_JSON).content(input)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
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