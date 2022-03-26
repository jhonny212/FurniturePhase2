package com.example.furniture.service.admin;

import com.example.furniture.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public boolean RegisterUser(Profile profile);
    public Page<Profile> FilterAllUsers(Optional<Integer> page, Optional<String> name,Optional<Integer> rol);
    public Page<Profile> getAllUsers(Optional<Integer> page);
    public boolean removeUser(Integer id);
}
