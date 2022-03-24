package com.example.furniture.serviceImp.admin;

import com.example.furniture.model.Furniture;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.service.admin.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Override
    public List<Furniture> getReportMinFurnitureXPeriod(Optional<Date> date1, Optional<Date> date2){
        List<Furniture> list = this.furnitureRepository.findMinFurniture(date1.get(), date2.get());
        List<Double> list1 = this.furnitureRepository.findMinFurnitureNum(date1.get(), date2.get());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).amount = list1.get(i);
        }
        return list;
    }

    @Override
    public List<Furniture> getReportMaxFurnitureXPeriod(Optional<Date> date1, Optional<Date> date2){

        List<Furniture> list = this.furnitureRepository.findMaxFurniture(date1.get(), date2.get());
        List<Double> list1 = this.furnitureRepository.findMaxFurnitureNum(date1.get(), date2.get());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).amount = list1.get(i);
        }
        return list;
    }

    @Override
    public Object getReportEarningsXPeriod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page){
        List<Furniture> list = this.furnitureRepository.findMinFurniture(date1.get(), date2.get());
        List<Double> list1 = this.furnitureRepository.findMinFurnitureNum(date1.get(), date2.get());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).amount = list1.get(i);
        }
        return null;
    }

}
