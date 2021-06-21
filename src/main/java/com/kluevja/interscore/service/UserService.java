package com.kluevja.interscore.service;

import com.kluevja.interscore.entity.Role;
import com.kluevja.interscore.entity.UserEntity;
import com.kluevja.interscore.repository.UserRepository;
import com.kluevja.interscore.security.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(UserEntity logged) {
        UserEntity userEntity = (UserEntity)userRepository.findByEmail(logged.getEmail());
        return new AuthResponse(userEntity);
    }

    public AuthResponse register(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole(Role.ROLE_USER);
        userRepository.save(userEntity);
        return new AuthResponse(userEntity);
    }

    public boolean canLogin(UserEntity logged) {
        UserEntity userEntity = (UserEntity)userRepository.findByEmail(logged.getEmail());
        return userEntity != null && isPasswordMatches(logged.getPassword(), userEntity.getPassword());
    }

    private boolean isPasswordMatches(String unencodedPassword, String encodedPassword) {
        return passwordEncoder.matches(unencodedPassword, encodedPassword);
    }

    public boolean canRegister(UserEntity userEntity) {
        boolean isUserAlreadyExists = userRepository.existsByEmail(userEntity.getEmail());
        return !isUserAlreadyExists;
    }
}
