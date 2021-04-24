package com.kluevja.interscore.controller;

import com.kluevja.interscore.entity.UserEntity;
import com.kluevja.interscore.security.AuthResponse;
import com.kluevja.interscore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserEntity userEntity) {
        System.out.println("Принял LOG Запрос");
        if(userService.canLogin(userEntity)) {
            AuthResponse response = userService.login(userEntity);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("Не удалось войти в систему. Проверьте логин и пароль и повторите снова.");
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody UserEntity userEntity) {
        System.out.println("Принял REG Запрос");
        if(userService.canRegister(userEntity)) {
            System.out.println("Можешь зарегистрироваться");
            AuthResponse response = userService.register(userEntity);
            return ResponseEntity.ok().body(response);
        }
        System.out.println("Что-то пошло не так");
        return ResponseEntity.badRequest().body("Пользователь с данным именем или email уже существует.");
    }
}