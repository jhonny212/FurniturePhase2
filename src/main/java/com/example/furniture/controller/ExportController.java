package com.example.furniture.controller;

import com.example.furniture.model.reports.*;
import com.example.furniture.serviceImp.admin.CsvExportService;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin/export")
public class ExportController {
    @Autowired
    private CsvExportService csvExportService;

    @RequestMapping(path = "/report-sales-x-period")
    public ResponseEntity<Object> exportSalesReport(HttpServletResponse servletResponse, @RequestBody ArrayList<SalesReport> data) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvExportService.writeReportSalesXPeriod(servletResponse.getWriter(), data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/report-earnings-x-period")
    public ResponseEntity<Object> exportEarningsReport(HttpServletResponse servletResponse, @RequestBody ArrayList<EarningsReport> data) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvExportService.writeReportEarningsXPeriod(servletResponse.getWriter(), data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/report-min-furniture-x-period")
    public ResponseEntity<Object> exportLessFurnitureReport(HttpServletResponse servletResponse, @RequestBody ArrayList<MostLessSoldFurnitureReport> data) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvExportService.writeReportMaxMinFurnitureXPeriod(servletResponse.getWriter(), data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/report-max-furniture-x-period")
    public ResponseEntity<Object> exportMostFurnitureReport(HttpServletResponse servletResponse, @RequestBody ArrayList<MostLessSoldFurnitureReport> data) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvExportService.writeReportMaxMinFurnitureXPeriod(servletResponse.getWriter(), data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/report-return-furniture")
    public ResponseEntity<Object> exportReturnReport(HttpServletResponse servletResponse, @RequestBody ArrayList<ReturnReport> data) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvExportService.writeReportReturnFurnitureXPeriod(servletResponse.getWriter(), data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/report-best-seller-x-period")
    public ResponseEntity<Object> exportBestSellerReport(HttpServletResponse servletResponse, @RequestBody ArrayList<BestSellerReport> data) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvExportService.writeReportBestSellerXPeriod(servletResponse.getWriter(), data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/report-best-earner-x-period")
    public ResponseEntity<Object> exportBestEarnerReport(HttpServletResponse servletResponse, @RequestBody ArrayList<BestEarnerReport> data) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        csvExportService.writeReportBestEarnerXPeriod(servletResponse.getWriter(), data);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
