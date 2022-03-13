package com.example.furniture.controller.fabricate;

import com.example.furniture.model.Piece;
import com.example.furniture.serviceImp.fabricate.PieceServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
            if (piece.cost <=0 || piece.getPrice()<=0){
                piece.msj = "Ingrese un costo o precio mayor a 0";
                return piece;
            }
            return pieceServiceImp.createPiece(piece);
        }
        piece.msj = "Complete todos los campos";
        return piece;
    }

    @PostMapping("/update-piece")
    public Piece updatePiece(@RequestBody Piece piece){
        piece.setCost(0);
        if(validationService.validate(piece)){
            if ( piece.getPrice()<=0){
                piece.msj = "Ingrese un  precio mayor a 0";
                return piece;
            }
            Piece tmp = this.pieceServiceImp.getPieceById(piece.getId());
            if (tmp == null) {
                return new Piece("No existe la pieza");
            }
            return this.pieceServiceImp.updatePiece(tmp);
        }
        return new Piece("Complete los campos");
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
