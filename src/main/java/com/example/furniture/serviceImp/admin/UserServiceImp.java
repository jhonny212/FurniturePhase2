package com.example.furniture.serviceImp.admin;

import com.example.furniture.model.Profile;
import com.example.furniture.repository.admin.UserRepositoryAdmin;
import com.example.furniture.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepositoryAdmin userRepository;

    @Override
    public boolean RegisterUser(Profile profile) {
        Optional<Profile> prof = userRepository.findByUsername(profile.getUsername());
        if(prof.isPresent()){
            return false;
        }else{
            try {
                userRepository.save(profile);
                return  true;
            }catch (org.hibernate.exception.ConstraintViolationException ex){
                return false;
            }
        }
    }

    @Override
    public Page<Profile> getAllUsers(Optional<Integer> page) {
        Page<Profile> list= this.userRepository.findAllByUsernameContainsAndStatusEquals(
                "",true,
                PageRequest.of(
                        page.orElse(0),2
                )
        );
        return list;
    }
    @Override
    public Page<Profile> FilterAllUsers(Optional<Integer> page, Optional<String> name,Optional<Integer> rol){
        if(rol.isPresent()){
            if(rol.get()==-1){
                return this.userRepository.findAllByUsernameContainsAndStatusEquals(
                        name.orElse(""),true,
                        PageRequest.of(
                                page.orElse(0),2
                        )
                );
            }
            return this.userRepository.findAllByUsernameContainsAndUserTypeEqualsAndStatusEquals(
                    name.orElse(""),rol.orElse(0),true,
                    PageRequest.of(
                            page.orElse(0),2
                    )
            );
        }else{
            return this.userRepository.findAllByUsernameContainsAndStatusEquals(
                    name.orElse(""),true,
                    PageRequest.of(
                            page.orElse(0),2
                    )
            );
        }

    }
    @Override
    public boolean removeUser(Integer id) {
        try{
            Optional<Profile> profile = this.userRepository.findById(id);
            if(profile.isPresent()){
                profile.get().setStatus(false);
                this.userRepository.save(profile.get());
                return true;
            }
            return false;
        }catch (Exception ex){
            return false;
        }
    }
}
