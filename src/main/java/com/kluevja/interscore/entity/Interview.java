package com.kluevja.interscore.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Interview {
    @Id
    private Long id;
    private String name;
    private String place;
    private String date;
    private boolean isAccepted;
    @ManyToOne
    private User interviewer;
    @ManyToOne
    private User interviewee;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Question> questions;
}
