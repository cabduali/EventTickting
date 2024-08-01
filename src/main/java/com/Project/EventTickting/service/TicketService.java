package com.Project.EventTickting.service;

import com.Project.EventTickting.model.Ticket;
import com.Project.EventTickting.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(long id) {
        return ticketRepository.findById(id);
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteById(long id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> searchTickets(String query) {
        return ticketRepository.searchTickets(query);
    }
}
