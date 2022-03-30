package com.example.furniture.controller.admin;

import com.example.furniture.model.BillDetails;
import com.example.furniture.model.Furniture;
import com.example.furniture.serviceImp.admin.BillServiceImp;
import com.example.furniture.serviceImp.admin.ReportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/report")
public class ReportController {

    @Autowired
    private BillServiceImp billServiceImp;
    @Autowired
    private ReportServiceImp reportServiceImp;

    @GetMapping("/report-max-furniture-x-period")
    public List<Furniture> getMaxFurnitureXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2
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

        return this.reportServiceImp.getReportMaxFurnitureXPeriod(d1, d2);
    }

    @GetMapping("/report-min-furniture-x-period")
    public List<Furniture> getMinFurnitureXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2
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

        return this.reportServiceImp.getReportMinFurnitureXPeriod(d1, d2);
    }

    @GetMapping("/report-earnings-x-period")
    public Page<BillDetails> getEarningsXPeriod(
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

        return this.billServiceImp.getReportEarningsXPeriod(d1, d2, page);
    }

    @GetMapping("/report-best-seller-x-period")
    public Object getBestSellerXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2
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

        return this.billServiceImp.gerReportBestSellerXPeriod(d1, d2);
    }

    @GetMapping("/report-best-earner-x-period")
    public Object getBestEarnerXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2
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

        return this.billServiceImp.getReportBestEarnerXPeriod(d1, d2);
    }
}
