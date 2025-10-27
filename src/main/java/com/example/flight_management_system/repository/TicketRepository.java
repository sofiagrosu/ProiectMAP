package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {
    private List<Ticket> tickets =  new ArrayList<>();
    private long nextId = 1;

    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setId(String.valueOf(nextId));
            nextId++;
        }
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets);
    }

    public Ticket findById(String id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId().equals(id)) {
                return ticket;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        Ticket ticket = findById(id);
        if (ticket != null) {
            tickets.remove(ticket);
            return true;
        }
        return false;
    }
}
