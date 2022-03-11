package com.example.furniture.service.fabricate;

import com.example.furniture.model.Piece;

public interface PieceService {

    public Piece createPiece(Piece piece);
    public Piece getPieceById(Integer id);
    public Piece updatePiece(Piece piece);
}
