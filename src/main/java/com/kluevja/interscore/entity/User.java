package com.kluevja.interscore.entity;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean isActive;
    private String name;
    private String surname;
    private String patronymic;
    private String birthday;
    private String sex;
    private String location;
    private String phone;
    @Email
    private String email;
    @Size(min = 8, max = 32)
    private String password;
    private String langs;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}