package com.kluevja.interscore.security;

import com.kluevja.interscore.entity.Role;
import com.kluevja.interscore.entity.UserEntity;
import lombok.Getter;

@Getter
public class AuthResponse {
    private Long id;
    private String email;
    private String photo;
    private Role role;

    public AuthResponse(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.role = userEntity.getRole();
        this.photo = userEntity.getPhoto();
    }
}
