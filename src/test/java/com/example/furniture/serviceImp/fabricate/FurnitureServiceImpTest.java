package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Furniture;
import com.example.furniture.repository.fabricate.CategoryRepository;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FurnitureServiceImpTest {

    @Mock
    private FurnitureRepository furnitureRepository;
    @InjectMocks
    private FurnitureServiceImp furnitureServiceImp;
    Furniture furniture;
    @BeforeEach
    void setUp() {
        furniture = new Furniture();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getFurniture() {
        Optional<Furniture> f = Optional.of(furniture);
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(f);
        assertEquals(furnitureServiceImp.getFurniture(1),furniture);

    }

    @Test
    void postFurniture() {
        Mockito.when(
                furnitureRepository.save(furniture)
        ).thenReturn(furniture);
        assertEquals(furnitureServiceImp.postFurniture(furniture),furniture);
    }

    @Test
    void getAllFurniture() {
        Page<Furniture> p=Page.empty(Pageable.unpaged());
        Mockito.when(
          furnitureRepository.findAll(Mockito.any(Pageable.class))
        ).thenReturn(p);
        Optional<Integer> pp = Optional.of(2);
        assertEquals(furnitureServiceImp.getAllFurniture(pp),p);
    }

    @Test
    void testGetAllFurniture() {
        Page<Furniture> p=Page.empty(Pageable.unpaged());
        Mockito.when(
                furnitureRepository.findByNameContains(Mockito.any(String.class),Mockito.any(Pageable.class))
        ).thenReturn(p);
        Optional<Integer> pp = Optional.of(2);
        Optional<String> f = Optional.of("f");
        assertEquals(furnitureServiceImp.getAllFurniture(f,pp),p);
    }

    @Test
    void getAllFurnitureFilter() throws Exception{
        Page<Furniture> p=Page.empty(Pageable.unpaged());
        Mockito.when(
                furnitureRepository.findByCreationDateBetween(
                        Mockito.any(Date.class),
                        Mockito.any(Date.class),
                        Mockito.any(Pageable.class))
        ).thenReturn(p);
        Optional<Integer> pp = Optional.of(2);
        Optional<String> f = Optional.of("f");
        Optional<Date> d1 = Optional.of(new Date());
        assertEquals(furnitureServiceImp.getAllFurnitureFilter(d1,d1,pp,pp),p);
    }

    @Test
    void isExisteFurniture() {
        Optional<Furniture> f = Optional.of(new Furniture());
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(f);
        assertTrue(furnitureServiceImp.isExisteFurniture(1));
    }

    @Test
    void searchFurniture() {
        Optional<Furniture> f = Optional.of(new Furniture());
        Mockito.when(
          furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(f);
        assertEquals(furnitureServiceImp.searchFurniture(1),f);
    }

    @Test
    void updateFurniture() {
        Optional<Furniture> tmp = Optional.of(furniture);
        Mockito.when(
                furnitureServiceImp.searchFurniture(Mockito.anyInt())
        ).thenReturn(tmp);
        Mockito.when(
                furnitureServiceImp.postFurniture(Mockito.any(Furniture.class))
        ).thenReturn(furniture);
        assertEquals(furnitureServiceImp.updateFurniture(1),"1");
    }

    //----

    @Test
    void getFurnituresOnSale() {
        Page<Furniture> p=Page.empty(Pageable.unpaged());
        Mockito.when(
                furnitureRepository.findByStatusAndNameContains(
                        Mockito.anyInt(),
                        Mockito.anyString(),
                        Mockito.any(Pageable.class)
                )
        ).thenReturn(p);
        Optional<String> name = Optional.of("f");
        Optional<Integer> page = Optional.of(1);
        assertEquals(furnitureServiceImp.getFurnituresOnSale(name,page),p);
    }
}