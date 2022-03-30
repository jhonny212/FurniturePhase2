package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Furniture;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.sales.FurnitureInBillRepository;
import com.example.furniture.service.sales.FurnitureInBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureInBillServiceImp implements FurnitureInBillService {

    @Autowired
    private FurnitureInBillRepository furnitureInBillRepository;

    @Override
    public List<FurnitureInBill> getFurnituresInBillBySession(Profile profile) {
        return this.furnitureInBillRepository.findByProfile(profile);
    }

    @Override
    public boolean removeFurnitureFromBill(Profile profile, Furniture furniture) {
        FurnitureInBill furnitureInBill = this.furnitureInBillRepository.findByFurnitureAndProfile(furniture, profile);
        this.furnitureInBillRepository.delete(furnitureInBill);
        return !this.furnitureInBillRepository.existsById(furnitureInBill.getId());
    }

    @Override
    public boolean addFurnitureToBill(FurnitureInBill furnitureInBill) {
        this.furnitureInBillRepository.save(furnitureInBill);
        return this.furnitureInBillRepository.existsById(furnitureInBill.getId());
    }

    @Override
    public void deleteAllFurnituresInBillFromSession(Profile profile) {
        this.furnitureInBillRepository.deleteFurnitureInBillByProfile(profile);
    }
}
