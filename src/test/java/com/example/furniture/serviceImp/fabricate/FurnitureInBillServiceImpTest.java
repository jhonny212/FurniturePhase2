package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.repository.fabricate.CategoryRepository;
import com.example.furniture.repository.sales.FurnitureInBillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FurnitureInBillServiceImpTest {

    @Mock
    private FurnitureInBillRepository furnitureInBillRepository;

    @InjectMocks
    private FurnitureInBillServiceImp furnitureInBillServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getFurnituresInBillBySession() {
        List<FurnitureInBill> list  = new ArrayList<>();
        Mockito.when(
                furnitureInBillRepository.findByProfile(Mockito.anyInt())
        ).thenReturn(list);
        assertNotNull(furnitureInBillServiceImp.getFurnituresInBillBySession(1));
        assertEquals(furnitureInBillServiceImp.getFurnituresInBillBySession(1),list);
    }

    @Test
    void removeFurnitureFromBill() {
        Mockito.when(
                furnitureInBillRepository.existsById(Mockito.anyInt())
        ).thenReturn(false);
        assertEquals(furnitureInBillServiceImp.removeFurnitureFromBill(1),true);
    }

    @Test
    void addFurnitureToBill() {
        FurnitureInBill furniture = new FurnitureInBill();
        furniture.setId(1);
        Mockito.when(
                furnitureInBillRepository.save(Mockito.any(FurnitureInBill.class))
        ).thenReturn(furniture);
        Mockito.when(
                furnitureInBillRepository.existsById(Mockito.any(Integer.class))
        ).thenReturn(true);
        assertEquals(furnitureInBillServiceImp.addFurnitureToBill(furniture),true);
    }
}