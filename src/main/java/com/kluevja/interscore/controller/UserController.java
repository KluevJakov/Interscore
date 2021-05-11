package com.kluevja.interscore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kluevja.interscore.entity.UserEntity;
import com.kluevja.interscore.repository.UserRepository;
import com.kluevja.interscore.security.AuthResponse;
import com.kluevja.interscore.service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile/{id:\\d+}")
    public ResponseEntity userPage(@AuthenticationPrincipal UserPrincipal principal, @PathVariable("id") UserEntity user) {
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body("Данного пользователя не найдено");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal UserPrincipal principal) {
        System.out.println("Словил");
        System.out.println(userRepository.findAll().toString());
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserEntity userEntity) {
        if(userService.canLogin(userEntity)) {
            AuthResponse response = userService.login(userEntity);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("Неверный логин или пароль.");
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody UserEntity userEntity) {
        if(userService.canRegister(userEntity)) {
            AuthResponse response = userService.register(userEntity);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("Пользователь с данным именем или email уже существует.");
    }
}