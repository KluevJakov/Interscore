package com.kluevja.interscore.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Test {
    @Id
    private long id;
    private String name;
    private String discribtion;
    private boolean isAccepted;
    @ManyToMany
    private Set<Option> options;
    @ManyToMany
    private Set<Category> categories;
}
