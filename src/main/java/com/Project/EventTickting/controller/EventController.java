package com.Project.EventTickting.controller;

import com.Project.EventTickting.model.Event;
import com.Project.EventTickting.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public String getAllEvents(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "events";
    }

    @GetMapping("/events/new")
    public String showCreateEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "create_event";
    }

    @PostMapping("/events")
    public String createEvent(@ModelAttribute("event") Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_event";
        }
        eventService.save(event);
        return "redirect:/events";
    }

    @GetMapping("/events/edit/{id}")
    public String showEditEventForm(@PathVariable("id") long id, Model model) {
        Event event = eventService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        model.addAttribute("event", event);
        return "edit_event";
    }

    @PostMapping("/events/update/{id}")
    public String updateEvent(@PathVariable("id") long id, @ModelAttribute("event") Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            event.setId(id);
            return "edit_event";
        }
        eventService.save(event);
        return "redirect:/events";
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable("id") long id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/events/search")
    public String searchEvents(@RequestParam("query") String query, Model model) {
        List<Event> events = eventService.searchEvents(query);
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/events/view/{id}")
    public String viewEvent(@PathVariable("id") long id, Model model) {
        Event event = eventService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        model.addAttribute("event", event);
        return "view_event";
    }

}
