package com.kluevja.interscore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kluevja.interscore.entity.Option;
import com.kluevja.interscore.entity.Poll;
import com.kluevja.interscore.entity.UserEntity;
import com.kluevja.interscore.repository.PollRepository;
import com.kluevja.interscore.repository.UserRepository;
import com.kluevja.interscore.security.AuthResponse;
import com.kluevja.interscore.service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PollRepository pollRepository;

    @GetMapping("/profile/{id:\\d+}")
    public ResponseEntity userPage(@AuthenticationPrincipal UserPrincipal principal, @PathVariable("id") UserEntity user) {
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body("Данного пользователя не найдено");
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers(@AuthenticationPrincipal UserPrincipal principal) {
        //System.out.println("Словил");
        //System.out.println(userRepository.findAll().get(0).getRole().getAuthority());
        return userRepository.findAll();
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserEntity userEntity) {
        System.out.println("!!!!");
        System.out.println(userEntity.getEmail()+" "+userEntity.getPassword());
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

    @PostMapping("/pollCreate")
    public String pollCreate(@RequestBody Poll pollEntity) {
        System.out.println(pollEntity);
        //System.out.println(pollEntity.getName());
        //System.out.println(pollEntity.getTests());
        //pollEntity.setInterviewee();
        //pollRepository.save(pollEntity);
        return "success";
    }

    @PostMapping("/uploadFile/{id}")
    public ResponseEntity uploadFile(@RequestBody String photoUrl, @PathVariable Long id) {
        System.out.println(photoUrl);
        Optional<UserEntity> user = userRepository.findById(id);

        if(user.isPresent()){
            UserEntity userPreset = user.get();
            userPreset.setPhoto(photoUrl);
            userRepository.save(userPreset);
        }

        return ResponseEntity.ok().body("hi");
    }
}