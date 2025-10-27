package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository implements GenericRepository<Ticket> {
    private List<Ticket> tickets =  new ArrayList<>();
    private long nextId = 1;

    @Override
    public void save(Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setId(String.valueOf(nextId));
            nextId++;
        }

    }
    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(tickets);
    }
    @Override
    public Ticket findById(String id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId().equals(id)) {
                return ticket;
            }
        }
        return null;
    }
    @Override
    public boolean delete(Ticket ticket) {
        return tickets.remove(ticket);
    }
}
