package com.example.furniture.controller.sales;


import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import com.example.furniture.serviceImp.sales.InvoiceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/sales/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImp invoiceServiceImp;

    @GetMapping("/get-bill-cliente")
    public Page<BillDetails> getBillClient(
            @RequestParam Optional<Integer> billId,
            @RequestParam Optional<Integer> page
    ){
        return this.invoiceServiceImp.getBillsClient(billId, page);
    }
}
