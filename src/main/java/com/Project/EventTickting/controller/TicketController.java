package com.Project.EventTickting.controller;

import com.Project.EventTickting.model.Event;
import com.Project.EventTickting.model.Ticket;
import com.Project.EventTickting.service.EventService;
import com.Project.EventTickting.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;

    @GetMapping("/tickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.findAll());
        return "tickets";
    }

    @GetMapping("/tickets/new")
    public String showCreateTicketForm(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("events", events);
        return "create_ticket";
    }

    @PostMapping("/tickets")
    public String createTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Event> events = eventService.findAll();
            model.addAttribute("events", events);
            return "create_ticket";
        }
        ticketService.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/edit/{id}")
    public String showEditTicketForm(@PathVariable("id") long id, Model model) {
        Ticket ticket = ticketService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));
        List<Event> events = eventService.findAll();
        model.addAttribute("ticket", ticket);
        model.addAttribute("events", events);
        return "edit_ticket";
    }

    @PostMapping("/tickets/update/{id}")
    public String updateTicket(@PathVariable("id") long id, @ModelAttribute("ticket") Ticket ticket, BindingResult result, Model model) {
        if (result.hasErrors()) {
            ticket.setId(id);
            List<Event> events = eventService.findAll();
            model.addAttribute("events", events);
            return "edit_ticket";
        }
        ticketService.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/delete/{id}")
    public String deleteTicket(@PathVariable("id") long id) {
        ticketService.deleteById(id);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/search")
    public String searchTickets(@RequestParam("query") String query, Model model) {
        List<Ticket> tickets = ticketService.searchTickets(query);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/tickets/view/{id}")
    public String viewTicket(@PathVariable("id") long id, Model model) {
        Ticket ticket = ticketService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));
        model.addAttribute("ticket", ticket);
        return "view_ticket";
    }
}
