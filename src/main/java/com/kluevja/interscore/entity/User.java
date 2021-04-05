package com.kluevja.interscore.entity;

import javax.persistence.*;
import java.util.Set;

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
    private String email;
    private String password;
    private String langs;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}