package com.kluevja.interscore.entity;

import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private UserEntity sender;
    @ManyToOne
    private UserEntity receiver;
}
