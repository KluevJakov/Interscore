package com.kluevja.interscore.repository;

import com.kluevja.interscore.entity.Interview;
import com.kluevja.interscore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findAll();
    Set<Interview> findAllByDate(String date);
    Set<Interview> findAllByInterviewerDate(User interviewer, String date);
    Set<Interview> findAllByIntervieweeDate(User interviewee, String date);
    Set<Interview> findAllByInterviewer(User interviewer);
    Set<Interview> findAllByInterviewee(User interviewee);
}
