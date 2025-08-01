package com.school.security.controller;

import com.school.security.entity.AppUser;
import com.school.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/findAllUsers")
    public List<AppUser> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/findUserById/{id}")
    public AppUser findUserById(Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/deleteUser/{id}")
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/insertUser")
    public AppUser insertUser(AppUser appUser) {
        return userService.insertUser(appUser);
    }

    @GetMapping("/findUserByUsername/{username}")
    public AppUser findUserByUsername(String username) {
        return userService.findUserByUsername(username);
    }


}
