package com.example.furniture.repository.profile;

import com.example.furniture.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    public Profile findByUsername(String username);
}
