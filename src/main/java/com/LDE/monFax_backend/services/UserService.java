package com.LDE.monFax_backend.services;

import com.LDE.monFax_backend.models.User;
import com.LDE.monFax_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getLast5ConnectedUsers() {
        return userRepository.findTop5ByOrderByLastLoginDesc();
    }
}