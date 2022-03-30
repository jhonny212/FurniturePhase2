package com.example.furniture.serviceImp.sales;

import com.example.furniture.model.Client;
import com.example.furniture.repository.sales.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImpTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImp clientServiceImp;

    @Test
    void getClient() {
        //---
        Client client = new Client(1,"","");
        Optional<Client> op = Optional.of(client);
        Mockito.when(
                clientRepository.findById(Mockito.any(Integer.class))
        ).thenReturn(op);
        assertEquals(clientServiceImp.getClient(1),client);
    }

    @Test
    void createClient() {
        Client client = new Client(1,"s","");
        Mockito.when(
                clientRepository.save(Mockito.any(Client.class))
        ).thenReturn(client);
        Mockito.when(
                clientRepository.existsById(Mockito.anyInt())
        ).thenReturn(true);
        assertTrue(clientServiceImp.createClient(client));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}