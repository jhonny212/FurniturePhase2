package com.example.furniture.repository.fabricate;

import com.example.furniture.model.Category;
import com.example.furniture.model.Furniture;
import com.example.furniture.util.FurnitureM;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FurnitureRepository extends JpaRepository<Furniture,Integer> {

    Page<Furniture> findByNameContains(String name, Pageable page);
    Page<Furniture> findByCreationDateBetween(Date date1, Date date2, Pageable page);
    Page<Furniture> findByStatus(Integer status, Pageable page);

    //REPORTS
    @Query(value = "SELECT f.*, (COUNT(billd.id_furniture)) AS amount FROM \n" +
            "\"furniture\" AS F LEFT JOIN \"bill_details\" AS billd ON f.code=billd.id_furniture INNER JOIN \"bill\" AS bill \n" +
            "ON billd.id_bill=bill.id_bill \n" +
            "WHERE f.code=billd.id_furniture AND bill.datetime between ? and ? \n" +
            "GROUP BY billd.id_furniture, f.code ORDER BY amount desc LIMIT 10;", nativeQuery = true)
    List<Furniture> findMaxFurniture(Date date1, Date date2);

    @Query(value = "SELECT (COUNT(billd.id_furniture)) AS amount FROM \n" +
            "\"furniture\" AS F LEFT JOIN \"bill_details\" AS billd ON f.code=billd.id_furniture INNER JOIN \"bill\" AS bill \n" +
            "ON billd.id_bill=bill.id_bill \n" +
            "WHERE f.code=billd.id_furniture AND bill.datetime between ? and ? \n" +
            "GROUP BY billd.id_furniture, f.code ORDER BY amount desc LIMIT 10;", nativeQuery = true)
    List<Double> findMaxFurnitureNum(Date date1, Date date2);

    @Query(value = "SELECT f.*, (COUNT(billd.id_furniture)) AS amount FROM \n" +
            "\"furniture\" AS F LEFT JOIN \"bill_details\" AS billd ON f.code=billd.id_furniture INNER JOIN \"bill\" AS bill \n" +
            "ON billd.id_bill=bill.id_bill \n" +
            "WHERE f.code=billd.id_furniture AND bill.datetime between ? and ? \n" +
            "GROUP BY billd.id_furniture, f.code ORDER BY amount asc LIMIT 10;", nativeQuery = true)
    List<Furniture> findMinFurniture(Date date1, Date date2);

    @Query(value = "SELECT (COUNT(billd.id_furniture)) AS amount FROM \n" +
            "\"furniture\" AS F LEFT JOIN \"bill_details\" AS billd ON f.code=billd.id_furniture INNER JOIN \"bill\" AS bill \n" +
            "ON billd.id_bill=bill.id_bill \n" +
            "WHERE f.code=billd.id_furniture AND bill.datetime between ? and ? \n" +
            "GROUP BY billd.id_furniture, f.code ORDER BY amount asc LIMIT 10;", nativeQuery = true)
    List<Double> findMinFurnitureNum(Date date1, Date date2);

//    @Query(value = "", nativeQuery = true)
//    Object findEarnings(Date date1, Date date2);
//
//    @Query(value = "", nativeQuery = true)
//    Object findEarnings(Date date1, Date date2);
//
//    @Query(value = "", nativeQuery = true)
//    Object findEarnings(Date date1, Date date2);\
//    Page<Furniture> findAllBy
}
