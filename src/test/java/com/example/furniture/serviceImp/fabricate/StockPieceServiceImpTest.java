package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Piece;
import com.example.furniture.model.StockPiece;
import com.example.furniture.repository.fabricate.StockPieceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockPieceServiceImpTest {

    @Mock
    StockPieceRepository stockPieceRepository;
    @InjectMocks
    private StockPieceServiceImp stockPieceServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addInStock() {
        List<StockPiece> pieces = new ArrayList<>();
        Piece p = new Piece();
        p.setStock(1);
        p.setId(1);
        Mockito.when(
                stockPieceRepository.saveAll(Mockito.anyList())
        ).thenReturn(pieces);
        assertTrue(stockPieceServiceImp.addInStock(p));
    }
}