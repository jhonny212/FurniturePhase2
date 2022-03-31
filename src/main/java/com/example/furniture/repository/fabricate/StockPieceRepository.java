package com.example.furniture.repository.fabricate;

import com.example.furniture.model.StockPiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockPieceRepository extends JpaRepository<StockPiece,Integer> {
    List<StockPiece> findAllByIdAndStatus(Integer id, Integer status);
}
