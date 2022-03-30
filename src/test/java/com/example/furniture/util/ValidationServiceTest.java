package com.example.furniture.util;

import com.example.furniture.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {
    @InjectMocks
    ValidationService validationService;
    @Test
    void validate() {
        Client client = new Client();
        assertEquals(validationService.validate(client),false);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}