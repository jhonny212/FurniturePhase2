package com.example.furniture.repository.user;

import com.example.furniture.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Profile,Integer> {

    public Profile findByUsername(String username);
}
