package com.example.furniture.serviceImp.sales;

import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.repository.sales.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceImpTest {

    @Mock
    private BillRepository billRepository;
    @Mock
    private BillDetailsRepository billDetailsRepository;
    @InjectMocks
    private InvoiceServiceImp invoiceServiceImp;
    Page<BillDetails> pageBills;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pageBills = Page.empty();
    }

    @Test
    void getBillsClient() {
        Optional<Integer> billId = Optional.of(1);
        Optional<Integer> page = Optional.of(1);
        Mockito.when(
                billDetailsRepository.findAllByBill_Id(Mockito.any(Integer.class),Mockito.any(Pageable.class)
                )
        ).thenReturn(pageBills);
        assertEquals(invoiceServiceImp.getBillsClient(billId,page),pageBills);
    }

    @Test
    void getReturnClient() {
        Optional<Integer> nit = Optional.of(1);
        Optional<Date > date1 = Optional.empty();
        Optional<Date> date2 = Optional.empty();
        Optional<Integer> page = Optional.empty();
        Mockito.when(
                billDetailsRepository.findAllByBill_Client_IdAndDateReturnNotNullAndDateReturnBetween(
                        Mockito.anyInt(),Mockito.any(Date.class),Mockito.any(Date.class),Mockito.any(Pageable.class)
                )
        ).thenReturn(pageBills);
        assertEquals(invoiceServiceImp.getReturnClient(nit,date1,date1,page),pageBills);
    }

    @Test
    void getSalesToday() {
        Optional<Date> date2 = Optional.of(new Date());
        Optional<Integer> page = Optional.empty();
        Mockito.when(
                billDetailsRepository.findAllByBill_DateTime(
                        Mockito.any(Date.class),Mockito.any(Pageable.class)
                )
        ).thenReturn(pageBills);
        assertEquals(invoiceServiceImp.getSalesToday(date2,page),pageBills);
    }

    @Test
    void getBills() {
        Page<Bill> pbill = Page.empty();
        Optional<String> client = Optional.of("s");
        Optional<Date> date2 = Optional.of(new Date());
        Optional<Integer> page = Optional.empty();
        Mockito.when(
                billRepository.findByClient_NameContainsAndDateTimeBetween(
                        Mockito.anyString(),Mockito.any(Date.class),
                        Mockito.any(Date.class),Mockito.any(Pageable.class)
                )
        ).thenReturn(pbill);
        assertEquals(invoiceServiceImp.getBills(client,date2,date2,page),pbill);
    }

}