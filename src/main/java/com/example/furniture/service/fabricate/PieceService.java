package com.example.furniture.service.fabricate;

import com.example.furniture.model.Piece;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface PieceService {

    public Piece createPiece(Piece piece);
    public Piece getPieceById(Integer id);
    public Piece updatePiece(Piece piece);

    //Hecho, sin probar
    public boolean deletePiece(Integer id);

    //Hecho, sin probar (Tanto para listar todas las piezas como para filtar por nombre)
    public Page<Piece> getAllPieces(Optional<Integer> pageNumber, Optional<String> name);
}
