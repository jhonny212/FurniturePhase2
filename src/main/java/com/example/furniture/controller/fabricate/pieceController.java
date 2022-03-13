package com.example.furniture.controller.fabricate;

import com.example.furniture.model.Piece;
import com.example.furniture.serviceImp.fabricate.PieceServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.HashMap;
import java.util.Optional;

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

    @GetMapping("/get-all")
    public Page<Piece> getAllPieces(@RequestParam Optional<Integer> page,@RequestParam Optional<String> name){
        return this.pieceServiceImp.getAllPieces(page,name);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String,String> deletePiece(@PathVariable("id") Integer id){
        HashMap<String,String> response = new HashMap<String,String>();
        if(this.pieceServiceImp.deletePiece(id)){
            response.put("msj","Se ha eliminado la pieza con éxito");
        }else{
            response.put("msj","Ha ocurrido un error por lo que la pieza no se ha eliminado.");
        }
        return response;
    }

}
