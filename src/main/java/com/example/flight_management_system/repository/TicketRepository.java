package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository extends InFileRepository<Ticket>  {

    public TicketRepository() {
        super("tickets.json", Ticket.class);
    }
}
