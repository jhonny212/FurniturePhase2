package com.example.furniture.repository.sales;

import com.example.furniture.model.BillDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Integer> {

    Page<BillDetails> findAllByBill_Id(Integer id_bill,Pageable pageable);
}