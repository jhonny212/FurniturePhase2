package com.example.furniture.repository.fabricate;

import com.example.furniture.model.StockPiece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPieceRepository extends JpaRepository<StockPiece,Integer> {
}
