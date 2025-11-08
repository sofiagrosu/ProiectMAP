package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketController extends AbstractCrudController<Ticket>{
    public TicketController(CrudService<Ticket> service) {
        super(service,
                "/tickets",
                "tickets/index",
                "tickets/form",
                "tickets",
                "ticket",
                Ticket::new);
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("ticket") Ticket ticket) {
        service.save(ticket);
        return "redirect:/tickets";
    }
}
