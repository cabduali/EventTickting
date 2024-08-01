package com.Project.EventTickting.service;

import com.Project.EventTickting.model.Event;
import com.Project.EventTickting.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> findById(long id) {
        return eventRepository.findById(id);
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> searchEvents(String query) {
        return eventRepository.searchEvents(query);
    }

    public Object getAllEvents() {
        return null;
    }
}
