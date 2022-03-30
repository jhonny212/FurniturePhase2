package com.example.furniture.serviceImp.sales;

import com.example.furniture.model.BillDetails;
import com.example.furniture.model.Furniture;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.repository.sales.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SaleFurnitureServiceImpTest {

    @Mock
    private BillDetailsRepository billDetailsRepository;
    @Mock
    private BillRepository billRepository;
    @Mock
    private FurnitureRepository furnitureRepository;
    @InjectMocks
    private SaleFurnitureServiceImp saleFurnitureServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void repaymentFurniture() {
        Furniture furniture = new Furniture();
        Optional<BillDetails> tmp = Optional.of(new BillDetails());
        Mockito.when(
                furnitureRepository.save(Mockito.any(Furniture.class))
        ).thenReturn(furniture);
        Mockito.when(
                billDetailsRepository.findById(Mockito.anyInt())
        ).thenReturn(tmp);
        Mockito.when(
                billDetailsRepository.save(Mockito.any(BillDetails.class))
        ).thenReturn(tmp.get());
        assertEquals(saleFurnitureServiceImp.repaymentFurniture(furniture,1,new Date()),false);
    }

    @Test
    void getAllDetailsByBillId() {
        List<Object[]> list= new ArrayList<>();
        Mockito.when(
                billDetailsRepository.findAllDetailBils(Mockito.anyInt())
        ).thenReturn(list);
        assertEquals(saleFurnitureServiceImp.getAllDetailsByBillId(1),list);
    }

    @Test
    void getClientByIdBill() {
        Object obj=new Object();
        Mockito.when(
                billDetailsRepository.findClient(Mockito.anyInt())
        ).thenReturn(obj);
        assertEquals(saleFurnitureServiceImp.getClientByIdBill(1),obj);
    }

    @Test
    void getEarningsTotal() throws Exception{
        Optional<Date> d1 = Optional.empty();
        Object o = new Object();
        Mockito.when(
          billDetailsRepository.findEarnings(Mockito.any(Date.class),Mockito.any(Date.class))
        ).thenReturn(o);
        assertEquals(saleFurnitureServiceImp.getEarningsTotal(d1,d1),o);
    }
}