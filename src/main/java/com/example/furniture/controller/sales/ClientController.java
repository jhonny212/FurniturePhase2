package com.example.furniture.controller.sales;

import com.example.furniture.model.Client;
import com.example.furniture.serviceImp.sales.ClientServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/sales/client")
public class ClientController {

    @Autowired
    private ClientServiceImp clientServiceImp;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/{nit}")
    public HashMap<String, Object> getClient(@RequestParam Integer nit){
        HashMap<String, Object> response = new HashMap<>();
        Client client = this.clientServiceImp.getClient(nit);
        if(client != null){
            response.replace("found",true);
            response.put("client",client);
        }else{
            response.put("found",false);
        }
        return response;
    }

    @PostMapping("")
    public HashMap<String, Object> createClient(@RequestBody Client client){
        HashMap<String, Object> response = new HashMap<>();
        response.put("wasAdded",false);
        if (validationService.validate(client)) response.replace("created",this.clientServiceImp.createClient(client));
        return response;
    }
}
