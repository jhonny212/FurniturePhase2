package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.model.Furniture;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.model.Profile;
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
        Profile profile = new Profile();
        profile.setId(1);
        Mockito.when(
                furnitureInBillRepository.findByProfile(Mockito.any(Profile.class))
        ).thenReturn(list);
        assertNotNull(furnitureInBillServiceImp.getFurnituresInBillBySession(profile));
        assertEquals(furnitureInBillServiceImp.getFurnituresInBillBySession(profile),list);
    }

    @Test
    void removeFurnitureFromBill() {
        Profile profile = new Profile();
        profile.setId(1);
        Furniture furniture = new Furniture();
        furniture.setCode(1);
        FurnitureInBill furnitureInBill = new FurnitureInBill();
        furnitureInBill.setId(1);
        Mockito.when(
                furnitureInBillRepository.findByFurnitureAndProfile(Mockito.any(Furniture.class),Mockito.any(Profile.class))
        ).thenReturn(furnitureInBill);
        Mockito.when(
                furnitureInBillRepository.existsById(Mockito.anyInt())
        ).thenReturn(false);
        assertEquals(furnitureInBillServiceImp.removeFurnitureFromBill(profile,furniture),true);
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