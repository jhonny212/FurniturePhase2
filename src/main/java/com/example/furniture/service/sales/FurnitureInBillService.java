package com.example.furniture.service.sales;

import com.example.furniture.model.Furniture;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.model.Profile;

import java.util.List;

public interface FurnitureInBillService {

    public List<FurnitureInBill> getFurnituresInBillBySession(Profile profile);
    public boolean removeFurnitureFromBill(Profile profile, Furniture furniture);
    public boolean addFurnitureToBill(FurnitureInBill furnitureInBill);
    public void deleteAllFurnituresInBillFromSession(Profile profile);
}
