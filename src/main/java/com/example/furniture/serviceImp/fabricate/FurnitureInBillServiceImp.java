package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.service.sales.FurnitureInBillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureInBillServiceImp implements FurnitureInBillService {

    @Override
    public List<FurnitureInBill> getFurnituresInBillBySession(Integer idUser) {
        return null;
    }

    @Override
    public boolean removeFurnitureFromBill(Integer id) {
        return false;
    }

    @Override
    public boolean addFurnitureToBill(FurnitureInBill furnitureInBill) {
        return false;
    }
}
