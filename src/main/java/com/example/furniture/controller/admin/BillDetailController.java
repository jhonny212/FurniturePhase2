package com.example.furniture.controller.admin;

import com.example.furniture.model.BillDetails;
import com.example.furniture.repository.sales.BillRepository;
import com.example.furniture.serviceImp.admin.BillServiceImp;
import com.example.furniture.serviceImp.admin.ReportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/bill-details")
public class BillDetailController {

    @Autowired
    private BillServiceImp billServiceImp;
    @Autowired
    private ReportServiceImp reportServiceImp;

    @GetMapping("/report-sales-x-period")
    public ResponseEntity<List<BillDetails>> getReportSalesXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2
    ) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("9999-01-01");
        if (!date1.isPresent()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isPresent()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        return new ResponseEntity<>(this.billServiceImp.gerReportSalesXperiod(d1, d2), HttpStatus.OK);
    }

}
