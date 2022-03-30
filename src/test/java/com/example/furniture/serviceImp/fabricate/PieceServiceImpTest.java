package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Piece;
import com.example.furniture.repository.fabricate.PieceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PieceServiceImpTest {

    @InjectMocks
    private PieceServiceImp pieceServiceImpTest;
    @Mock
    private PieceRepository pieceRepository;
    @InjectMocks
    private StockPieceServiceImp stockPieceServiceImp;
    Piece piece;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        piece = new Piece();
    }

    @Test
    void createPiece() {
        piece.setStock(0);
        Mockito.when(
                pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(piece);
        piece.msj = "Pieza registrada";
        assertEquals(pieceServiceImpTest.createPiece(piece),piece);
    }

    @Test
    void getPieceById() {
        Optional<Piece> pieces = Optional.of(piece);
        Mockito.when(
                pieceRepository.findById(Mockito.anyInt())
        ).thenReturn(pieces);
        assertEquals(pieceServiceImpTest.getPieceById(1),piece);
    }

    @Test
    void updatePiece() {
        Mockito.when(
                pieceRepository.save(piece)
        ).thenReturn(piece);
        piece.msj = "Pieza actualizada";
        assertEquals(pieceServiceImpTest.updatePiece(piece),piece);
    }

    @Test
    void getAllPieces() {
        Page<Piece> pieces = Page.empty();
        Mockito.when(
                pieceRepository.findByNameContains(Mockito.anyString(),Mockito.any(Pageable.class))
        ).thenReturn(pieces);
        Optional<Integer> op = Optional.of(1);
        Optional<String> name = Optional.of("s");
        assertEquals(pieceServiceImpTest.getAllPieces(op,name),pieces);
    }

    @Test
    void deletePiece() {
        Mockito.when(
                pieceRepository.existsById(Mockito.anyInt())
        ).thenReturn(false);
        assertFalse(pieceServiceImpTest.deletePiece(1));
    }
}