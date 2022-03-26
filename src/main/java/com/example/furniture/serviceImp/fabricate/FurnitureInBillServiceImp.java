package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.FurnitureInBill;
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
    public List<FurnitureInBill> getFurnituresInBillBySession(Integer idUser) {
        return this.furnitureInBillRepository.findByProfile(idUser);
    }

    @Override
    public boolean removeFurnitureFromBill(Integer id) {
        this.furnitureInBillRepository.deleteById(id);
        return !this.furnitureInBillRepository.existsById(id);
    }

    @Override
    public boolean addFurnitureToBill(FurnitureInBill furnitureInBill) {
        this.furnitureInBillRepository.save(furnitureInBill);
        return this.furnitureInBillRepository.existsById(furnitureInBill.getId());
    }
}
