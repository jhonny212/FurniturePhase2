package com.example.furniture.repository.fabricate;

import com.example.furniture.model.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece,Integer> {
    
}
