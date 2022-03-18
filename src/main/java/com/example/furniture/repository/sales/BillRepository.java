package com.example.furniture.repository.sales;

import com.example.furniture.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    Page<Bill> findBillByClientId(Integer nit, Pageable page);

}