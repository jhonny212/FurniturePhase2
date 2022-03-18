package com.example.furniture.controller.sales;


import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import com.example.furniture.serviceImp.sales.InvoiceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/get-return-client")
    public Page<BillDetails> getReturnClient(
            @RequestParam Optional<Integer> nit,
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2,
            @RequestParam Optional<Integer> page
    ) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("2100-01-01");
        if (!date1.isEmpty()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isEmpty()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        return this.invoiceServiceImp.getReturnClient(nit, d1, d2, page);
    }


}
