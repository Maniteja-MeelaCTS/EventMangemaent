package com.spring.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ems.dto.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}
