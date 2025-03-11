package com.spring.ems.service;

import com.spring.ems.dto.Event;
import com.spring.ems.dto.Organizer;
import com.spring.ems.repo.EventRepository;
import com.spring.ems.repo.OrganizerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    public Event createEvent(String name, String category, String location, LocalDateTime date, Long organizerId) {
        Organizer organizer = organizerRepository.findById(organizerId).orElseThrow(() -> new IllegalArgumentException("Invalid organizer ID"));
        Event event = new Event();
        event.setName(name);
        event.setCategory(category);
        event.setLocation(location);
        event.setDate(date);
        event.setOrganizer(organizer);
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }
}
