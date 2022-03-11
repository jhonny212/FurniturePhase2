package com.example.furniture.controller.fabricate;

import com.example.furniture.model.Piece;
import com.example.furniture.serviceImp.fabricate.PieceServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/fabricate/piece")
public class pieceController {

    @Autowired
    private PieceServiceImp pieceServiceImp;
    @Autowired
    private ValidationService validationService;

    @PostMapping("/register-piece")
    public Piece createPiece(@RequestBody Piece piece){
        if(validationService.validate(piece)){
            if (piece.cost <=0){
                piece.msj = "Ingrese un costo mayor a 0";
                return piece;
            }
            return pieceServiceImp.createPiece(piece);
        }
        piece.msj = "Complete todos los campos";
        return piece;
    }

    @PostMapping("/update-piece")
    public Piece updatePiece(@RequestBody Piece piece){
        Piece tmp = this.pieceServiceImp.getPieceById(piece.getId());
        if (tmp == null) {
            return new Piece("No existe la pieza");
        }
        this.validationService.updateVal(tmp, piece);
        return this.pieceServiceImp.updatePiece(tmp);
    }

    @GetMapping("/get-piece/{id}")
    public Piece getPieceById(@PathVariable("id") Integer id){
        Piece t= this.pieceServiceImp.getPieceById(id);
        if(t==null){
            return new Piece("No existe la pieza");
        }else{
            return t;
        }
    }

}
