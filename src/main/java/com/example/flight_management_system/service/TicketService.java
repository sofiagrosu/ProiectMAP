package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository repo;
    public TicketService(TicketRepository repo){ this.repo = repo; }
    public List<Ticket> findAll(){ return repo.findAll(); }
    public Ticket findById(String id){ return repo.findById(id).orElse(null); }
    public Ticket save(Ticket t){ return repo.save(t); }
    public void delete(String id){ repo.findById(id).ifPresent(repo::delete); }
}
