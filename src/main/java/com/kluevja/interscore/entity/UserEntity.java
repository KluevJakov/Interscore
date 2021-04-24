package com.kluevja.interscore.entity;

import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isActive;
    private String name;
    private String surname;
    private String patronymic;
    /*@Temporal(value = TemporalType.DATE)*/
    private String birthday;
    private String sex;
    private String photo;
    private String location;
    private String phone;
    @Email
    private String email;
    @Size(min = 8, max = 32)
    private String password;
    private String langs;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return id == userEntity.id;
    }

    public boolean isAdmin(){
        return this.role.equals(Role.ADMIN);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    public List<Role> getRoles(){
        return new ArrayList<Role>(Collections.singleton(role));
    }
}