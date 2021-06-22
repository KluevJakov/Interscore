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

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(UserEntity logged) {
        UserEntity userEntity = userRepository.findByEmail(logged.getEmail()).get();
        return new AuthResponse(userEntity);
    }

    public AuthResponse register(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole(Role.ROLE_USER);
        userEntity.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(userEntity);
        mailService.activate(userEntity);
        return new AuthResponse(userEntity);
    }

    public boolean activate(String code){
        Optional<UserEntity> checkCode = userRepository.findByActivationCode(code);
        if(checkCode.isPresent()){
            UserEntity activateIt = checkCode.get();
            activateIt.setActivationCode(null);
            userRepository.save(activateIt);
            return true;
        }else{
            return false;
        }
    }

    public boolean canLogin(UserEntity logged) {
        UserEntity userEntity = userRepository.findByEmail(logged.getEmail()).get();
        boolean activateCheck = false;
        if(userEntity.getActivationCode() == null){
            activateCheck = true;
        }
        return activateCheck && userEntity != null && isPasswordMatches(logged.getPassword(), userEntity.getPassword());
    }

    private boolean isPasswordMatches(String unencodedPassword, String encodedPassword) {
        return passwordEncoder.matches(unencodedPassword, encodedPassword);
    }

    public boolean canRegister(UserEntity userEntity) {
        boolean isUserAlreadyExists = userRepository.existsByEmail(userEntity.getEmail());
        return !isUserAlreadyExists;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> currentUser = userRepository.findByEmail(username);
        if(currentUser.isPresent()) {
            return currentUser.get();
        }else{
            throw new UsernameNotFoundException("Пользователь не найден");
        }
    }
}
