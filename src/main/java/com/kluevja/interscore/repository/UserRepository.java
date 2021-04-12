package com.kluevja.interscore.repository;

import com.kluevja.interscore.entity.Interview;
import com.kluevja.interscore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u")
    List<User> findAll();
    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    UserDetails findByEmail(String email);
}