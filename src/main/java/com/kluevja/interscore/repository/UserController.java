package com.kluevja.interscore.repository;

import com.kluevja.interscore.controller.UserRepository;
import com.kluevja.interscore.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/user")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}