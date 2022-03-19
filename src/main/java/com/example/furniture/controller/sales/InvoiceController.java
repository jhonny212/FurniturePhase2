package com.example.furniture.controller.sales;


import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import com.example.furniture.serviceImp.sales.InvoiceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/get-sale-today")
    public Page<BillDetails> getSalesToday(@RequestParam Optional<Integer> page) throws ParseException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = formatter1.parse(dtf.format(LocalDateTime.now())+" 23:59:59");
        Date date = formatter1.parse(dtf.format(LocalDateTime.now()));

        Optional<Date> d1 = Optional.of(date);

        return this.invoiceServiceImp.getSalesToday(d1, page);
    }

    @GetMapping("/get-sales-clients")
    public Page<Bill> getSalesClient(
            @RequestParam Optional<String> client,
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

        return this.invoiceServiceImp.getBills(client, d1, d2, page);
    }

    @GetMapping("/get-bill")
    public Page<BillDetails> getSalesClient(@RequestParam Optional<Integer> idBill){
        return null;
    }


}
