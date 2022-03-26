package com.example.furniture.repository.sales;

import com.example.furniture.model.Client;
import com.example.furniture.model.FurnitureInBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FurnitureInBillRepository extends JpaRepository<FurnitureInBill,Integer> {

    public FurnitureInBill findByFurnitureAndProfile(Integer code, Integer idUser);
    public List<FurnitureInBill> findByProfile(Integer idUser);

}
