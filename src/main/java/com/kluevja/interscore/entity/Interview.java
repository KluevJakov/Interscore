package com.kluevja.interscore.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String place;
    /*@Temporal(value = TemporalType.DATE)*/
    private String date;
    private boolean isAccepted;
    @ManyToOne
    private UserEntity interviewer;
    @ManyToOne
    private UserEntity interviewee;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Question> questions;
}
