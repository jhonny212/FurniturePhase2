package com.example.furniture.service.sales;

import com.example.furniture.model.BillDetails;
import com.example.furniture.model.Client;
import com.example.furniture.model.Furniture;

import java.util.Date;
import java.util.List;

public interface SaleFurnitureService {

    public boolean repaymentFurniture(Furniture furniture, int id, Date date);

    public List<Object[]> getAllDetailsByBillId(int id);

    public Object  getClientByIdBill(int id);

}
