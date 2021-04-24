package com.kluevja.interscore.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Category {
    @Id
    private Long id;
    @Size(min = 4, max = 20)
    private String name;
    @OneToOne
    private Category parent;

    public Category() {
    }
}
