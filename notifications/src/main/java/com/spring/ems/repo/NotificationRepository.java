package com.spring.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ems.dto.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
