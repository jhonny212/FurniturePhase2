package com.example.furniture.service.admin;

import com.example.furniture.model.Furniture;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReportService {

    public List<Furniture> getReportMinFurnitureXPeriod(Optional<Date> date1, Optional<Date> date2);

    public List<Furniture> getReportMaxFurnitureXPeriod(Optional<Date> date1, Optional<Date> date2);

    public Object getReportEarningsXPeriod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page);

}
