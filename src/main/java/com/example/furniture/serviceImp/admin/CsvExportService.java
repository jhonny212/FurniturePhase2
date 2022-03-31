package com.example.furniture.serviceImp.admin;

import com.example.furniture.model.reports.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

@Service
public class CsvExportService {

    public void writeReportSalesXPeriod(Writer writer, ArrayList<SalesReport> sales){
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("cliente","fecha","mueble","precio");
            for(SalesReport sale : sales){
                csvPrinter.printRecord(sale.getClient(),sale.getDate(),sale.getFurniture(),sale.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///report-earnings-x-perio
    public void writeReportEarningsXPeriod(Writer writer, ArrayList<EarningsReport> earnings){
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("codigo_mueble","no_factura","mueble","precio_total","costo","perdida","ganancias");
            for(EarningsReport earning : earnings){
                csvPrinter.printRecord(earning.getCodeFurniture(),earning.getInvoiceNumber(),earning.getFurniture(),
                        earning.getTotalPrice(),earning.getCost(),earning.getLost(),earning.getEarning());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //report-min-furniture-x-period
    public void writeReportMaxMinFurnitureXPeriod(Writer writer, ArrayList<MostLessSoldFurnitureReport> sales){
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("codigo_mueble","fecha","precio","cantidad","mueble");
            for(MostLessSoldFurnitureReport sale : sales){
                csvPrinter.printRecord(sale.getCodeFurniture(),sale.getDate(),sale.getPrice(),sale.getAmount(),sale.getNameFurniture());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeReportReturnFurnitureXPeriod(Writer writer, ArrayList<ReturnReport> returns){
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("nit","mueble","fecha","precio_venta","costo");
            for(ReturnReport returnReport : returns){
                csvPrinter.printRecord(returnReport.getNitCliente(),returnReport.getFurniture(),returnReport.getDate(),returnReport.getSalePrice(),returnReport.getCost());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeReportBestSellerXPeriod(Writer writer, ArrayList<BestSellerReport> bestSellers){
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id_usuario","nombre","apellido","ventas");
            for(BestSellerReport bestSeller : bestSellers){
                csvPrinter.printRecord(bestSeller.getIdUsuario(),bestSeller.getName(),bestSeller.getSurname(),bestSeller.getSalesAmount());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeReportBestEarnerXPeriod(Writer writer, ArrayList<BestEarnerReport> bestEarners){
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id_usuario","nombre","apellido","ganancias");
            for(BestEarnerReport bestEarner : bestEarners){
                csvPrinter.printRecord(bestEarner.getIdUsuario(),bestEarner.getName(),bestEarner.getSurname(),bestEarner.getEarnings());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
