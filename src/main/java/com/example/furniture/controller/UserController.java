package com.example.furniture.controller;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.fabricate.CategoryRepository;
import com.example.furniture.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
			if(profile.getPassword().equals(pwd)){
				response.put("token",jwtaf.getJWTToken(profile.getUsername(),profile.getUserType()));
			}else{
				response.put("msj","La contraseña ingresada no es la correcta");
			}
		}else{
			response.put("msj","No existe el usuario ingresado en el sistema");
		}
		return response;
	}

	@PostMapping("/isAdminLoggedIn")
	public boolean isAdminLoggedIn(@RequestHeader("Authorization") String token){
		JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
		Claims claims = jwtaf.getClaimsFromToken(token);
		if(((Integer)claims.get("user_type")) == 0) return true;
		return false;
	}

	@PostMapping("/isSalesmanLoggedIn")
	public boolean isSalesmanLoggedIn(@RequestHeader("Authorization") String token){
		JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
		Claims claims = jwtaf.getClaimsFromToken(token);
		if(((Integer)claims.get("user_type")) == 1) return true;
		return false;
	}

	@PostMapping("/isFabricatemanLoggedIn")
	public boolean isFabricatemanLoggedIn(@RequestHeader("Authorization") String token){
		JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
		Claims claims = jwtaf.getClaimsFromToken(token);
		if(((Integer)claims.get("user_type")) == 2) return true;
		return false;
	}
}
