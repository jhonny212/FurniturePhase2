package com.example.furniture.controller.fabricate;

import com.example.furniture.model.Piece;
import com.example.furniture.serviceImp.fabricate.PieceServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Piece> createPiece(@RequestBody Piece piece){
        if(validationService.validate(piece)){
            if (piece.cost <=0 || piece.getPrice()<=0){
                piece.msj = "Ingrese un costo o precio mayor a 0";
                return new ResponseEntity<>(piece, HttpStatus.BAD_REQUEST);
            }
            Piece p= pieceServiceImp.createPiece(piece);
            if(p.cod==0){
                return new ResponseEntity<>(p, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(p, HttpStatus.BAD_REQUEST);
        }
        piece.msj = "Complete todos los campos";
        return new ResponseEntity<>(piece, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-in-stock/{id}/{stock}/{cost}")
    public ResponseEntity<Boolean> addInStockPiece(@PathVariable("id") int id,
                                                   @PathVariable("stock") int stock,
                                                   @PathVariable("cost") double cost){
        if(stock > 0 && cost >0){
                        Piece tmp = this.pieceServiceImp.getPieceById(id);
                        if(tmp!=null){
                            tmp.setStock(tmp.getStock()+stock);
                            this.pieceServiceImp.addInStock(tmp,stock,cost);
                            return new ResponseEntity<>(true,HttpStatus.OK);
                        }
        }
        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update-piece")
    public ResponseEntity<Piece> updatePiece(@RequestBody Piece piece){
        piece.setCost(0);
        if(validationService.validate(piece)){
            if ( piece.getPrice()<=0){
                piece.msj = "Ingrese un  precio mayor a 0";
                return new ResponseEntity<>(piece,HttpStatus.BAD_REQUEST);
            }
            Piece tmp = this.pieceServiceImp.getPieceById(piece.getId());
            if (tmp == null) {
                return new ResponseEntity<>(new Piece("La pieza no existe"),HttpStatus.BAD_REQUEST);
            }
            piece.setStock(tmp.getStock());
            return new ResponseEntity<>(this.pieceServiceImp.updatePiece(piece),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new Piece("Complete los campos"),HttpStatus.BAD_REQUEST);
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
