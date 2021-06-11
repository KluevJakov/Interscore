package com.kluevja.interscore.repository;

import com.kluevja.interscore.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByInterviewerId(Long id);
    List<Poll> findByIntervieweeId(Long id);
}
