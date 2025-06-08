package com.LDE.monFax_backend.controllers;

import com.LDE.monFax_backend.models.User;
import com.LDE.monFax_backend.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {
    private final UserService UserService;

    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping("/users/last5")
    public List<User> getLast5ConnectedUsers() {
        return UserService.getLast5ConnectedUsers();
    }
    
}