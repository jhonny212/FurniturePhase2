package com.example.furniture.repository.sales;

import com.example.furniture.model.BillDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Integer> {

    Page<BillDetails> findAllByBill_Id(Integer id_bill,Pageable pageable);
    Page<BillDetails> findAllByBill_Client_Id(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNull(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNotNull(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNotNullAndDateReturnBetween(Integer nit, Date date1, Date date2, Pageable pageable);
    Page<BillDetails> findAllByBill_DateTime(Date date,Pageable pageable);
}