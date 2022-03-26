package com.example.furniture.controller.admin;

import com.example.furniture.model.Profile;
import com.example.furniture.serviceImp.admin.UserServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private ValidationService validationService;

    @PostMapping()
    public ResponseEntity<Profile> registerUser(@RequestBody Profile profile){
        boolean valid = validationService.validate(profile);
        if (valid){
            valid = userServiceImp.RegisterUser(profile);
            if(valid){
                return new ResponseEntity<>(profile,HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(profile,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/todo")
    public Page<Profile> getAllUsers(@RequestParam Optional<Integer> page,
                                     @RequestParam Optional<String> name,
                                     @RequestParam Optional<Integer> role
                                     ){
        if(name.isPresent() || role.isPresent() ){
            return this.userServiceImp.FilterAllUsers(page,name,role);
        }
        return this.userServiceImp.getAllUsers(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeUser(@PathVariable(name = "id") int id){
        boolean valid = this.userServiceImp.removeUser(id);
        if (valid){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}