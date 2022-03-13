package com.example.furniture.serviceImp.authentication;

import com.example.furniture.model.Profile;
import com.example.furniture.repository.fabricate.CategoryRepository;
import com.example.furniture.repository.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class JwtProfileDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override //metodo que debo cambiar para ir a revisar a la base de datos
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByUsername(username);
        if (profile != null) {
            return new User(profile.getUsername(),profile.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
