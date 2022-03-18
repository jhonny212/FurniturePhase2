package com.example.furniture.controller.sales;

import com.example.furniture.model.Furniture;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.serviceImp.fabricate.FurnitureServiceImp;
import com.example.furniture.serviceImp.sales.SaleFurnitureServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/sale/furniture")
public class SaleFurnitureController {
    @Autowired
    private SaleFurnitureServiceImp saleFurnitureServiceImp;
    @Autowired
    private FurnitureServiceImp furnitureServiceImp;
    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @GetMapping("/devolution/{cod}/bill/{id}")
    public HashMap<String,Object> RefundFurniture(@PathVariable(name = "id") int id,@PathVariable(name = "cod") int cod){
        Date tmp = billDetailsRepository.getDetail(id,cod);
        HashMap<String,Object> response = new HashMap<>();
        String msj = "Error al realizar devolucion, intente de nuevo";
        int code = 400;
        if (tmp !=null){
            try {
                String Dateinicio     = tmp.toString();
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio      = date.parse(Dateinicio);
                Date fechaactual = new Date(System.currentTimeMillis());
                int milisecondsByDay = 86400000;
                int dias = (int) ((fechaactual.getTime()-fechaInicio.getTime()) / milisecondsByDay);
                if (dias < 7){
                    Furniture fun = furnitureServiceImp.getFurniture(cod);
                    if(fun.getPlan() !=null){
                        boolean valid = saleFurnitureServiceImp.repaymentFurniture(fun,id,fechaactual);
                        if(valid){
                            code = 200;
                            msj = "Devolucion realizada";
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        response.put("status_code",code);
        response.put("msj",msj);
        return  response;
    }

    @GetMapping("/{id}")
    public List<Object[]> getDetailBill(@PathVariable(name = "id") int id){
        return saleFurnitureServiceImp.getAllDetailsByBillId(id);
    }

    @GetMapping("/client/{id}")
    public Object getDetailClientByBillId(@PathVariable(name = "id")int id){
        return saleFurnitureServiceImp.getClientByIdBill(id);
    }

}
