package com.kluevja.interscore.service;

import com.kluevja.interscore.entity.Role;
import com.kluevja.interscore.entity.UserEntity;
import com.kluevja.interscore.repository.UserRepository;
import com.kluevja.interscore.security.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

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
        userEntity.setRole(Role.USER);
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails u = userRepository.findByEmail(s);
        if(u == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            return u;
        }
    }
}
