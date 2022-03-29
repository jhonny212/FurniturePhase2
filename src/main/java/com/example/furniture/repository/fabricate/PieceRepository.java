package com.example.furniture.repository.fabricate;

import com.example.furniture.model.Piece;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PieceRepository extends JpaRepository<Piece,Integer> {

    public Page<Piece> findByNameContainsIgnoreCase(String name, Pageable page);

}
