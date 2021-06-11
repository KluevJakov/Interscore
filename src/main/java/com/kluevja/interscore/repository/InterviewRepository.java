package com.kluevja.interscore.repository;

import com.kluevja.interscore.entity.Interview;
import com.kluevja.interscore.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByInterviewerId(Long id);
    List<Interview> findByIntervieweeId(Long id);
}
