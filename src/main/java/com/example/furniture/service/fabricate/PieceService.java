package com.example.furniture.service.fabricate;

import com.example.furniture.model.Piece;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface PieceService {

    public Piece createPiece(Piece piece);
    public Piece getPieceById(Integer id);
    public Piece updatePiece(Piece piece);
    public boolean deletePiece(Integer id);
    public Page<Piece> getAllPieces(Optional<Integer> pageNumber, Optional<String> name);
}
