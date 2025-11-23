package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.service.FlightService;
import com.example.flight_management_system.service.PassengerService;
import com.example.flight_management_system.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController extends AbstractCrudController<Ticket> {

    private final FlightService flightService;
    private final PassengerService passengerService;

    public TicketController(
            TicketService ticketService,
            FlightService flightService,
            PassengerService passengerService
    ) {
        super(ticketService,
                "/tickets",
                "tickets/index",
                "tickets/form",
                "tickets",
                "ticket",
                Ticket::new);

        this.flightService = flightService;
        this.passengerService = passengerService;
    }

    // --- FORM pentru creare ---
    @GetMapping("/new")
    @Override
    public String form(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("passengers", passengerService.findAll());
        model.addAttribute("flights", flightService.findAll());
        return "tickets/form";
    }

    // --- CREATE special (override pe create-ul generic din abstract) ---
    @Override
    @PostMapping
    public String create(@ModelAttribute("ticket") Ticket ticket) {

        // din form vin doar id-urile în passenger.id și flight.id
        String passengerId = ticket.getPassenger() != null ? ticket.getPassenger().getId() : null;
        String flightId = ticket.getFlight() != null ? ticket.getFlight().getId() : null;

        Passenger passenger = passengerService.findById(passengerId);
        Flight flight = flightService.findById(flightId);

        ticket.setPassenger(passenger);
        ticket.setFlight(flight);

        service.save(ticket);
        return "redirect:/tickets";
    }

    // --- FORM pentru edit ---
    @GetMapping("/{id}/edit")
    @Override
    public String editForm(@PathVariable String id, Model model) {
        Ticket ticket = service.findById(id);
        if (ticket == null) {
            return "redirect:/tickets";
        }

        model.addAttribute("ticket", ticket);
        model.addAttribute("passengers", passengerService.findAll());
        model.addAttribute("flights", flightService.findAll());
        return "tickets/form";
    }

    // --- UPDATE special (în abstract NU mai ai @PostMapping pe update) ---
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id,
                         @ModelAttribute("ticket") Ticket ticket) {

        ticket.setId(id);

        String passengerId = ticket.getPassenger() != null ? ticket.getPassenger().getId() : null;
        String flightId = ticket.getFlight() != null ? ticket.getFlight().getId() : null;

        ticket.setPassenger(passengerService.findById(passengerId));
        ticket.setFlight(flightService.findById(flightId));

        service.update(ticket);
        return "redirect:/tickets";
    }
}
