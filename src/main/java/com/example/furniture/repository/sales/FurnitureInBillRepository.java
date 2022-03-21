package com.example.furniture.repository.sales;

import com.example.furniture.model.Client;
import com.example.furniture.model.FurnitureInBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureInBillRepository extends JpaRepository<FurnitureInBill,Integer> {

    public FurnitureInBill findFurnitureInBillByFurnitureAndProfile(Integer code, Integer idUser);
    public boolean deleteFurnitureInBillById(Integer id);

}
