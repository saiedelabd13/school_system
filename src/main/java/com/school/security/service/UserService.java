package com.school.security.service;

import com.school.security.entity.AppUser;
import com.school.security.reposatory.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<AppUser> findAllUsers() {
        return userRepo.findAll();
    }

    public AppUser findUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public AppUser insertUser(AppUser appUser) {
        return userRepo.save(appUser);
    }

    public AppUser findUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }


    public Object loadUserByUsername(String username) {
            return null;
    }
}
