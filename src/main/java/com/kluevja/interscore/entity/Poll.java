package com.kluevja.interscore.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private boolean isAccepted;
    @ManyToOne
    private User interviewer;
    @ManyToOne
    private User interviewee;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Test> tests;
}
