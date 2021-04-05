package com.kluevja.interscore.repository;

import com.kluevja.interscore.entity.Interview;
import com.kluevja.interscore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    @Query(value = "SELECT u FROM Interview u")
    List<Interview> findAll();
    @Query(value = "SELECT u FROM Interview u WHERE u.date = ?1")
    List<Interview> findAllByDate(String date);
    List<Interview> findAllByInterviewerDate(User interviewer, String date);
    List<Interview> findAllByIntervieweeDate(User interviewee, String date);
    List<Interview> findAllByInterviewer(User interviewer);
    List<Interview> findAllByInterviewee(User interviewee);
}
