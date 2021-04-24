package com.kluevja.interscore.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Question {
    @Id
    private Long id;
    private String name;
    private String discribtion;
    private String text;
    private boolean isAccepted;
    @ManyToMany
    private Set<Category> categories;
}
