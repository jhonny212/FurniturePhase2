package com.example.furniture.controller;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public HashMap<String, String> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		HashMap<String, String> response = new HashMap<>();
		JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
		Profile profile = this.userRepository.findByUsername(username);
		if(profile != null){
			if(profile.getPassword().equals(pwd) && profile.isStatus()){
				response.put("token",jwtaf.getJWTToken(profile.getUsername(),profile.getUserType(),profile.getId()));
			}else{
				response.put("msj","La contraseña ingresada no es la correcta");
			}
		}else{
			response.put("msj","No existe el usuario ingresado en el sistema");
		}
		return response;
	}

	@PostMapping("/isAdminLoggedIn")
	public boolean isAdminLoggedIn(@RequestHeader(value = "Authorization", required=false) String token){
		if(token != null){
			JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
			Claims claims = jwtaf.getClaimsFromToken(token);
			if (((Integer) claims.get("user_type")) == 0) return true;
		}
		return false;
	}

	@PostMapping("/isSalesmanLoggedIn")
	public boolean isSalesmanLoggedIn(@RequestHeader(value = "Authorization", required=false) String token){
		if(token != null){
			JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
			Claims claims = jwtaf.getClaimsFromToken(token);
			if (((Integer) claims.get("user_type")) == 1) return true;
		}
		return false;
	}

	@PostMapping("/isFabricatemanLoggedIn")
	public boolean isFabricatemanLoggedIn(@RequestHeader(value = "Authorization", required=false) String token){
		if(token != null){
			JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
			Claims claims = jwtaf.getClaimsFromToken(token);
			if(((Integer)claims.get("user_type")) == 2) return true;
		}
		return false;
	}

	@PostMapping("/isLoggedIn")
	public boolean isLoggedIn(@RequestHeader(value="Authorization",required = false) String token){
		if(token!=null){
			JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
			Claims claims = jwtaf.getClaimsFromToken(token);
			return !claims.isEmpty();
		}
		return false;
	}
}
