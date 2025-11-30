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
                             // We keep these required=false to avoid the MissingServletRequestParameterException when form is sent back on error
                             @RequestParam(value = "passengerId", required = false) Long passengerId,
                             @RequestParam(value = "flightId", required = false) Long flightId,
                             Model model) {

        // 1. JSR-303 Validation check (on Price, SeatNumber)
        if (result.hasErrors()) {
            // FIX: Manually set dummy objects to retain selected IDs in the dropdowns (th:selected)
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

        // 2. FINAL MAPPING: Map IDs to objects (No explicit validation, relies on DB integrity)
        try {
            // Only try to associate if an ID was actually selected (it might be null now)
            if (passengerId != null) {
                ticket.setPassenger(passengerRepository.findById(passengerId).orElse(null));
            }
            if (flightId != null) {
                ticket.setFlight(flightRepository.findById(flightId).orElse(null));
            }

            // Check if the relationships are still null after mapping (if they were NOT selected)
            // If the user submits without selecting an ID, this will rely on the DB's foreign key constraint

        } catch (Exception e) {
            // Relaunch the exception to be caught by GlobalExceptionHandler
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