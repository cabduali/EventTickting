package com.Project.EventTickting.repository;

import com.Project.EventTickting.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.location) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Event> searchEvents(String query);
}
