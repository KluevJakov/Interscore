package com.kluevja.interscore.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Category {
    @Id
    private long id;
    private Category parent;
    private String name;
    @ManyToMany
    private Set<Test> tests;
    @ManyToMany
    private Set<Question> questions;
}
