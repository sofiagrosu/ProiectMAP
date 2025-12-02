package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.TicketRepository;
import com.example.flight_management_system.repository.PassengerRepository;
import com.example.flight_management_system.repository.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public String listTickets(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "tickets/index";
    }

    @GetMapping("/new")
    public String createTicketForm(Model model, @RequestParam(required = false) Long flightId, @RequestParam(required = false) Long passengerId) {
        Ticket ticket = new Ticket();
        if (flightId != null) {
            ticket.setFlight(flightRepository.findById(flightId).orElse(null));
        }
        if (passengerId != null) {
            ticket.setPassenger(passengerRepository.findById(passengerId).orElse(null));
        }

        model.addAttribute("ticket", ticket);
        model.addAttribute("passengers", passengerRepository.findAll());
        model.addAttribute("flights", flightRepository.findAll());
        return "tickets/form";
    }

    @PostMapping("/save")
    public String saveTicket(@Valid @ModelAttribute("ticket") Ticket ticket,
                             BindingResult result,
                             @RequestParam(value = "passengerId", required = false) Long passengerId,
                             @RequestParam(value = "flightId", required = false) Long flightId,
                             Model model) {

        if (result.hasErrors()) {
            if (passengerId != null) {
                ticket.setPassenger(passengerRepository.findById(passengerId).orElse(new Passenger()));
                if (ticket.getPassenger().getId() == null) ticket.getPassenger().setId(passengerId);
            }
            if (flightId != null) {
                ticket.setFlight(flightRepository.findById(flightId).orElse(new Flight()));
                if (ticket.getFlight().getId() == null) ticket.getFlight().setId(flightId);
            }

            model.addAttribute("passengers", passengerRepository.findAll());
            model.addAttribute("flights", flightRepository.findAll());
            return "tickets/form";
        }

        try {
            if (passengerId != null) {
                ticket.setPassenger(passengerRepository.findById(passengerId).orElse(null));
            }
            if (flightId != null) {
                ticket.setFlight(flightRepository.findById(flightId).orElse(null));
            }


        } catch (Exception e) {
            throw new IllegalArgumentException("Error mapping relationships: " + e.getMessage());
        }

        ticketRepository.save(ticket);

        return "redirect:/tickets";
    }

    @GetMapping("/edit/{id}")
    public String editTicketForm(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));
        model.addAttribute("ticket", ticket);
        model.addAttribute("passengers", passengerRepository.findAll());
        model.addAttribute("flights", flightRepository.findAll());
        return "tickets/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable("id") Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));
        ticketRepository.delete(ticket);

        return "redirect:/tickets";
    }

    @GetMapping("/details/{id}")
    public String ticketDetails(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));
        model.addAttribute("ticket", ticket);
        return "tickets/details";
    }
}