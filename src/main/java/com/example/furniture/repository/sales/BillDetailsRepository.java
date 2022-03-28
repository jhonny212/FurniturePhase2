package com.example.furniture.repository.sales;

import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;


import java.util.List;

public interface BillDetailsRepository extends JpaRepository<BillDetails,Integer> {
    @Query(value = "SELECT b.datetime from bill_details  " +
            "inner join bill as b on bill_details.id_bill = b.id_bill " +
            "where bill_details.id = ? and " +
            "bill_details.id_furniture = ? " +
            ";", nativeQuery = true)
    Date getDetail(int id,int cod);

    @Query(value = "select bd.id_furniture,bd.price_sale,\n" +
            " f.name, bd.id, b.nit \n" +
            "from bill_details as bd inner join bill as b\n" +
            "on bd.id_bill = b.id_bill \n" +
            "inner join furniture as f\n" +
            "on bd.id_furniture = f.code\n" +
            "where b.id_bill = ? and bd.cost_lost = 0\n" +
            "",nativeQuery = true)
    List<Object[]> findAllDetailBils(int id);

    @Query(value = "select cl.name,cl.nit,cl.address from client as cl\n" +
            "inner join bill as b\n" +
            "on b.nit = cl.nit\n" +
            "where b.id_bill = ?;",nativeQuery = true)
    Object findClient(int id);

    @Query(value = "SELECT \n" +
            "\tSUM(billd.price_sale) AS sales, \n" +
            "\tSUM(billd.cost_lost) AS lost, \n" +
            "\tSUM(f.cost) AS costs,\n" +
            "\t(SUM(billd.price_sale) - SUM(f.cost) - SUM(billd.cost_lost)) AS earnings \n" +
            "\tFROM \"bill_details\" AS billd \n" +
            "\tLEFT JOIN \"bill\" AS bill \n" +
            "\tON billd.id_bill=bill.id_bill \n" +
            "\tLEFT JOIN \"furniture\" AS f ON f.code=billd.id_furniture\n" +
            "\tWHERE" +
            "\tbill.datetime BETWEEN ? AND ?;",nativeQuery = true)
    Object findEarnings(Date date1, Date date2);

    Page<BillDetails> findAllByBill(Bill id_bill, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_Id(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNull(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNotNull(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNotNullAndDateReturnBetween(Integer nit, Date date1, Date date2, Pageable pageable);
    Page<BillDetails> findAllByBill_DateTime(Date date,Pageable pageable);


    List<BillDetails> findByCostLostGreaterThanAndDateReturnIsBetween(double costLost,Date date1,Date date2);
    List<BillDetails> findByCostLostGreaterThan(double costLost);


    //PARA ADMIN
    Page<BillDetails> findAllByBill_DateTimeBetween(Date date1, Date date2, Pageable pageable);

//    Page<BillDetails> findAll(Pageable pageable);

//    @Query(value = "SELECT * FROM \"bill_details\" AS billd " +
//            "LEFT JOIN \"bill\" AS bill ON billd.id_bill=bill.id_bill " +
//            "WHERE bill.datetime BETWEEN '0001-01-01' AND '9999-01-01' LIMIT ?;", nativeQuery = true)
//    Page<BillDetails> getBillDetailsReport(Date date1, Date date2, Pageable pageable);

    @Query(value = "select f.\"name\",bd.cost_lost,\n" +
            "bd.date_return,bd.price_sale,b.nit,pf.username\n" +
            "from public.bill_details as bd\n" +
            "inner join public.bill as b ON b.id_bill = bd.id_bill\n" +
            "inner join public.profile as pf ON pf.id_user = b.id_user\n" +
            "inner join public.furniture as f on f.code = bd.id_furniture\n" +
            "where bd.cost_lost > ?;",nativeQuery = true)
    List<Object[]> getLost1(double lost);

    @Query(value = "select f.\"name\",bd.cost_lost,\n" +
            "bd.date_return,bd.price_sale,b.nit,pf.username\n" +
            "from public.bill_details as bd\n" +
            "inner join public.bill as b ON b.id_bill = bd.id_bill\n" +
            "inner join public.profile as pf ON pf.id_user = b.id_user\n" +
            "inner join public.furniture as f on f.code = bd.id_furniture\n" +
            "where bd.cost_lost > ? and bd.date_return BETWEEN ? and ?;",nativeQuery = true)
    List<Object[]> getLost2(double lost,Date d1,Date d2);
}

