package com.example.furniture.serviceImp.fabricate;

import java.util.Optional;
import com.example.furniture.model.Piece;
import com.example.furniture.repository.fabricate.PieceRepository;
import com.example.furniture.service.fabricate.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PieceServiceImp implements PieceService {

    @Autowired
    private PieceRepository pieceRepository;
    @Autowired
    private StockPieceServiceImp stockPieceServiceImp;

    @Override
    public Piece createPiece(Piece piece) {
        try {
            Piece tmp = this.pieceRepository.save(piece);
            if (tmp.getStock() > 0){
                this.stockPieceServiceImp.addInStock(piece);
            }
            tmp.msj = "Pieza registrada";
            return tmp;
        } catch (DataIntegrityViolationException e) {
            piece.msj ="Error al crear pieza, intente de nuevo";
        }
        piece.cod=1;
        return piece;
    }

    public void addInStock(Piece piece){
        this.stockPieceServiceImp.addInStock(piece);
    }

    @Override
    public Piece getPieceById(Integer id) {
        Optional<Piece> piece =  this.pieceRepository.findById(id);
        if (piece.isPresent()) {
            return piece.get();
        }else{
            return null;
        }
    }

    @Override
    public Piece updatePiece(Piece piece) {
        Piece tmp= this.pieceRepository.save(piece);
        tmp.msj = "Pieza actualizada";
        return tmp;
    }

    @Override
    public Page<Piece> getAllPieces(Optional<Integer> pageNumber, Optional<String> name){
        return this.pieceRepository.findByNameContainsIgnoreCase(
                name.orElse(""),
                PageRequest.of(
                        pageNumber.orElse(0),5
                )
        );
    }

    @Override
    public boolean deletePiece(Integer id){
        this.pieceRepository.deleteById(id);
        return this.pieceRepository.existsById(id);
    }

}