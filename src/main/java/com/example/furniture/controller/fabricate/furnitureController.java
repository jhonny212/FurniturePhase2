package com.example.furniture.controller.fabricate;

import com.example.furniture.model.*;
import com.example.furniture.repository.admin.AssignPlanPieceRepository;
import com.example.furniture.repository.fabricate.AssignFurniturePieceRepository;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.repository.fabricate.PieceRepository;
import com.example.furniture.repository.fabricate.StockPieceRepository;
import com.example.furniture.service.fabricate.FurnitureService;
import com.example.furniture.serviceImp.fabricate.FurnitureServiceImp;
import com.example.furniture.util.Utility;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/fabricate/furniture")
public class furnitureController {

    UUID uuid = UUID.randomUUID();

    @Autowired
    private FurnitureServiceImp furnitureServiceImp;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private FurnitureRepository furnitureRepository;
    @Autowired
    private Utility utilityService;
    @Autowired
    AssignPlanPieceRepository assignPlanPieceRepository;
    @Autowired
    PieceRepository pieceRepository;
    @Autowired
    StockPieceRepository stockPieceRepository;
    @Autowired
    AssignFurniturePieceRepository assignFurniturePieceRepository;

    @PostMapping("/register-furniture")
    public ResponseEntity<Furniture> registerFurniture(
            @RequestParam("file") MultipartFile file,
            @RequestParam("code") Integer code,
            @RequestParam("name") String name,
            @RequestParam("price") String price,
            @RequestParam("cost") String cost,
            @RequestParam("creationDate") String creationDate,
            @RequestParam("description") String description,
            @RequestParam("path") String path,
            @RequestParam("profile") String profile,
            @RequestParam("plan") String plan
    ) throws ParseException {

        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date date2=formatter2.parse(creationDate);
        Furniture furniture = new Furniture(code, name, Double.parseDouble(price), Double.parseDouble(cost), date2, description, path,
                new Profile(Integer.parseInt(profile), null, null, null, null, null),
                new Plan(Integer.parseInt(plan), null, null, true), 0);

        if (this.furnitureServiceImp.isExisteFurniture(code)){
            furniture.msj = "Ya existe un Mueble con el mismo Codigo";
            return new ResponseEntity<>(furniture,HttpStatus.BAD_REQUEST);
        }

        if(!file.isEmpty()){
            //String nameFile = utilityService.saveFile(file,"src/main/resources/img/");
            //furniture.setPath(nameFile);
        }else{
            furniture.setPath(null);
        }
        List<AssignPlanPiece> assignPlanPieces = this.assignPlanPieceRepository.findAllByPlan_Id(Integer.parseInt(plan));
        List<StockPiece> stockPieceList=new ArrayList<>();
        List<AssignFurniturePiece> assignFurniturePieceList = new ArrayList<>();
        double costo_total = 0;
        for (int i = 0; i < assignPlanPieces.size(); i++) {
            AssignPlanPiece a = assignPlanPieces.get(i);
            int id = a.getPiece().getId();
            int amount = assignPlanPieces.get(i).getAmount();
            List<StockPiece>  tmp = stockPieceRepository.findAllByIdAndStatus(id,0);
            if(tmp.size()<amount){
                furniture.msj = "No hay piezas suficientes para armar el mueble "+a.getPiece().getName();
                return new ResponseEntity<>(furniture,HttpStatus.BAD_REQUEST);
            }
            for (int j = 0; j < amount; j++) {
                tmp.get(j).setStatus(1);
                stockPieceList.add(tmp.get(j));
                costo_total+=tmp.get(j).getCost();
                AssignFurniturePiece assignFurniturePiece = new AssignFurniturePiece();
                assignFurniturePiece.setFurniture(furniture);
                assignFurniturePiece.setStockPiece(tmp.get(j));
                assignFurniturePieceList.add(assignFurniturePiece);
            }
        }
        furniture.setCost(costo_total);
        Furniture tmp = this.furnitureServiceImp.postFurniture(furniture);
        if(tmp!=null && stockPieceList.size()!=0 ){
            this.stockPieceRepository.saveAll(stockPieceList);
            this.assignFurniturePieceRepository.saveAll(assignFurniturePieceList);
            return new ResponseEntity<>(tmp,HttpStatus.BAD_REQUEST);
        }
        furniture.msj  = "Error al crear mueble, intente de nuevo";
        return new ResponseEntity<>(furniture,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-furniture")
    public Furniture getFurniture(
            @RequestParam Optional<Integer> code
    ){
        if(this.furnitureServiceImp.isExisteFurniture(code.get())){
            return furnitureServiceImp.getFurniture(code.get());
        }

        return new Furniture();
    }



    @GetMapping("/get-allFurniture")
    public Page<Furniture> getAllFuniture(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Integer> page
    ){
        if (filter.isPresent()){
            return this.furnitureServiceImp.getAllFurniture(filter, page);
        }
        return this.furnitureServiceImp.getAllFurniture(page);
    }

    @GetMapping("/get-allFurniture-filter")
    public Page<Furniture> getAllFunitureFilter(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2,
            @RequestParam Optional<Integer> sort,
            @RequestParam Optional<Integer> page
    ) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("2100-01-01");
        if (!date1.isEmpty()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isEmpty()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        return this.furnitureServiceImp.getAllFurnitureFilter(d1,d2,sort, page);
    }

    @PutMapping("/put-furniture-on-sale/")
    public Object putFurnitureOnSale(@RequestBody OnSaleData onSaleData){
        HashMap<String,Object> response= new HashMap<>();
        Furniture furniture = this.furnitureRepository.findById(onSaleData.getCode()).orElse(null);
        response.put("wasUpdated",false);
        if(furniture!=null){
            furniture.setPrice(onSaleData.getPrice());
            furniture.setStatus(1);
            this.furnitureRepository.save(furniture);
            response.replace("wasUpdated", true);
        }
        return response;
    }

    @GetMapping("/on-sale")
    public Page<Furniture> getFurnituresOnSale(@RequestParam Optional<Integer> page, @RequestParam Optional<String> name){
        return this.furnitureServiceImp.getFurnituresOnSale(name, page);
    }

    @GetMapping("/on-storage")
    public Page<Furniture> getFurnituresOnStorage(@RequestParam Optional<Integer> page, @RequestParam Optional<String> name){
        return this.furnitureServiceImp.getFurnituresOnStorage(name, page);
    }
}
