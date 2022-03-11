package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Piece;
import com.example.furniture.model.StockPiece;
import com.example.furniture.repository.fabricate.StockPieceRepository;
import com.example.furniture.service.fabricate.StockPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockPieceServiceImp implements StockPieceService {

    @Autowired
    StockPieceRepository stockPieceRepository;

    @Override
    public boolean addInStock(Piece piece) {
        List<StockPiece> pieces = new ArrayList<>();
        for (int i = 0; i < piece.getStock(); i++) {
            StockPiece t = new StockPiece();
            t.setStatus(0);
            t.setCost(piece.cost);
            t.setPiece(piece);
            pieces.add(t);
        }
        this.stockPieceRepository.saveAll(pieces);
        return true;
    }
}
