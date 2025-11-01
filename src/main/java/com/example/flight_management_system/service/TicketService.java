package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends CrudService<Ticket> {

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        super(ticketRepository);
    }
}
