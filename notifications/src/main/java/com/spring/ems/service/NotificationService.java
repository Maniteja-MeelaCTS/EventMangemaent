package com.spring.ems.service;

import com.spring.ems.dto.Event;
import com.spring.ems.dto.Notification;
import com.spring.ems.repo.EventRepository;
import com.spring.ems.repo.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EventRepository eventRepository;

    public Notification createNotification(Long userId, Long eventId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setEventId(eventId);
        notification.setMessage(message);
        notification.setSentTimestamp(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    public void notifyUserAboutEvent(Long userId, Long eventId, String eventDetails) {
        String message = "Event Details: " + eventDetails;
        createNotification(userId, eventId, message);
    }

    public void sendReminderForUpcomingEvent(Long userId, Long eventId, String eventDetails) {
        String message = "Reminder: Upcoming Event - " + eventDetails;
        createNotification(userId, eventId, message);
    }

    @Scheduled(fixedRate = 60000) // Check every minute
    public void checkEventReminders() {
        List<Event> events = eventRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (Event event : events) {
            LocalDateTime eventTime = event.getDate();
            if (eventTime.minusMinutes(60).isBefore(now) && eventTime.minusMinutes(60).isAfter(now.minusMinutes(1))) {
                // Send reminder 1 hour before the event
                sendReminderForUpcomingEvent(1L, event.getEventId(), event.getName() + " at " + event.getLocation());
            }
            if (eventTime.minusMinutes(30).isBefore(now) && eventTime.minusMinutes(30).isAfter(now.minusMinutes(1))) {
                // Send reminder 30 minutes before the event
                sendReminderForUpcomingEvent(1L, event.getEventId(), event.getName() + " at " + event.getLocation());
            }
        }
    }
}
