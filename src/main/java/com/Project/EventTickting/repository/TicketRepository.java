package com.Project.EventTickting.repository;

import com.Project.EventTickting.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE LOWER(t.attendeeName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(t.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Ticket> searchTickets(String query);
}
