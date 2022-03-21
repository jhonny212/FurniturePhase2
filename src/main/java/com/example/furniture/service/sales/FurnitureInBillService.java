package com.example.furniture.service.sales;

import com.example.furniture.model.FurnitureInBill;

import java.util.List;

public interface FurnitureInBillService {

    public List<FurnitureInBill> getFurnituresInBillBySession(Integer idUser);
    public boolean removeFurnitureFromBill(Integer id);
    public boolean addFurnitureToBill(FurnitureInBill furnitureInBill);
}
