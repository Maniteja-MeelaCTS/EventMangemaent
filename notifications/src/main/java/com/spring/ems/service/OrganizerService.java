package com.spring.ems.service;

import com.spring.ems.dto.Organizer;
import com.spring.ems.repo.OrganizerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepository organizerRepository;

    public Organizer createOrganizer(String name, String email, String password, String contactNumber) {
        Organizer organizer = new Organizer();
        organizer.setName(name);
        organizer.setEmail(email);
        organizer.setPassword(password);
        organizer.setContactNumber(contactNumber);
        return organizerRepository.save(organizer);
    }

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public Organizer getOrganizerById(Long organizerId) {
        return organizerRepository.findById(organizerId).orElse(null);
    }
}
