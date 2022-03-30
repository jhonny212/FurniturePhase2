package com.example.furniture.repository.sales;

import com.example.furniture.model.Client;
import com.example.furniture.model.Furniture;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface FurnitureInBillRepository extends JpaRepository<FurnitureInBill,Integer> {

    public FurnitureInBill findByFurnitureAndProfile(Furniture furniture, Profile profile);
    public List<FurnitureInBill> findByProfile(Profile profile);
    @Transactional
    public void deleteFurnitureInBillByProfile(Profile profile);

}
