package com.kluevja.interscore.repository;

import com.kluevja.interscore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /*
    @Query(value = "SELECT u FROM user_entity u")
     */
    List<UserEntity> findAll();
    /*
    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    */
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
}