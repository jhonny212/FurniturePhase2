package com.example.furniture.serviceImp.fabricate;

import com.example.furniture.model.Furniture;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.service.fabricate.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureServiceImp implements FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Override
    public Furniture getFurniture(Integer id){
        Optional<Furniture> tmp = this.furnitureRepository.findById(id);
        return tmp.orElseGet(() -> new Furniture("No existe el mueble"));
    }

    @Override
    public Furniture postFurniture(Furniture furniture){
        try{
//            if (isExisteFurniture(furniture.getCode())){
//                furniture.msj = "Ya existe un Mueble con el mismo Codigo";
//                return furniture;
//            }
            return this.furnitureRepository.save(furniture);
        }catch (DataIntegrityViolationException e){
            furniture.msj = "Ocurrio un Error al Registrar el Mueble";
            System.out.println(e);
            return furniture;
        }

    }

    @Override
    public Page<Furniture> getAllFurniture(Optional<Integer> page){
        List<Furniture> l = this.furnitureRepository.findAll();
        l.forEach( f -> System.out.println(f.getName()));
        return this.furnitureRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        10
                )
        );
    }
    @Override
    public Page<Furniture> getAllFurniture(Optional<String> filter, Optional<Integer> page){

        return this.furnitureRepository.findByNameContains(
                filter.orElse(""),
                PageRequest.of(page.orElse(0),10, Sort.by("name").descending())
        );
    }
    @Override
    public Page<Furniture> getAllFurnitureFilter(Optional<Date> date1, Optional<Date> date2, Optional<Integer> sort, Optional<Integer> page) throws ParseException {
        Sort s = Sort.by("name").ascending();
        if (!sort.isEmpty() && sort.get()==0){
            s = Sort.by("name").descending();
        }

        return this.furnitureRepository.findByCreationDateBetween(date1.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01")),
                date2.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("2100-01-01")),
                PageRequest.of(page.orElse(0), 10, s ));
    }

    public boolean isExisteFurniture(Integer id){
        Optional<Furniture> f = this.furnitureRepository.findById(id);
        if (f.isPresent()){
            return true;
        }
        return false;
    }

    public Optional<Furniture> searchFurniture(Integer id){
        return this.furnitureRepository.findById(id);
    }

    @Override
    public String updateFurniture(Integer id) {
        Optional<Furniture> tmp = this.searchFurniture(id);
        if (tmp.isPresent()) {
            tmp.get().setStatus(2);
            Furniture tmp2 = this.postFurniture(tmp.get());
            if (!(tmp2.msj.isBlank() || tmp2.msj.isEmpty())){
                return "Error al poner mueble en venta intente de nuevo";
            }
            return "1";
        }else{
            return "El mueble no existe";
        }
    }

    @Override
    public Optional<Furniture> getFurnitureById(Integer id){
        LocalDate todaysDate = LocalDate.now();

        return null;
    }

}
