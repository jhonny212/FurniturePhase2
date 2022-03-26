package com.example.furniture.repository.admin;

import com.example.furniture.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryAdmin extends JpaRepository<Profile,Integer> {
    Optional<Profile> findByUsername(String username);
    Page<Profile> findAllByUsernameContainsAndStatusEquals(String username,Boolean status, Pageable page);
    Page<Profile> findAllByUsernameContainsAndUserTypeEqualsAndStatusEquals(String username,Integer role,Boolean status,Pageable Page);
}
