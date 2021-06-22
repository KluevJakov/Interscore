package com.kluevja.interscore.repository;

import com.kluevja.interscore.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
