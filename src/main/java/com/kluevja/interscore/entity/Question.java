package com.kluevja.interscore.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String discribtion;
    private String text;
    private boolean isAccepted;
    @ManyToMany
    private Set<Category> categories;
}
