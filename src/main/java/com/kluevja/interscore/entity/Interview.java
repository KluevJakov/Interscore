package com.kluevja.interscore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String place;
    private String createDate;
    @JsonProperty
    private boolean accepted;
    @ManyToOne
    private UserEntity interviewer;
    @ManyToOne
    private UserEntity interviewee;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Question> questions;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", createDate='" + createDate + '\'' +
                ", accepted=" + accepted +
                ", interviewer=" + interviewer +
                ", interviewee=" + interviewee +
                ", questions=" + questions +
                '}';
    }
}
