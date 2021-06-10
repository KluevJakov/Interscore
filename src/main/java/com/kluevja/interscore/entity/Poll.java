package com.kluevja.interscore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean isAccepted;
    @ManyToOne
    private UserEntity interviewer;
    @ManyToOne
    private UserEntity interviewee;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Test> tests;

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAccepted=" + isAccepted +
                ", interviewer=" + interviewer +
                ", interviewee=" + interviewee +
                ", tests=" + tests +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public UserEntity getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(UserEntity interviewer) {
        this.interviewer = interviewer;
    }

    public UserEntity getInterviewee() {
        return interviewee;
    }

    public void setInterviewee(UserEntity interviewee) {
        this.interviewee = interviewee;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
