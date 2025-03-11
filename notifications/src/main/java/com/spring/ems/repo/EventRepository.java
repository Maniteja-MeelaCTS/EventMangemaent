package com.spring.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ems.dto.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
