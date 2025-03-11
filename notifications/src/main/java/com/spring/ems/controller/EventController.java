package com.spring.ems.controller;

import com.spring.ems.dto.Event;
import com.spring.ems.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public Event createEvent(@RequestParam String name, @RequestParam String category, @RequestParam String location, @RequestParam String date, @RequestParam Long organizerId) {
        LocalDateTime eventDateTime = LocalDateTime.parse(date);
        return eventService.createEvent(name, category, location, eventDateTime, organizerId);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}
