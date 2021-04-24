package com.kluevja.interscore.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Option {
    @Id
    private Long id;
    private String text;
    private boolean isTrue;
}
