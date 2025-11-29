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

import java.util.List;

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
    public String createTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("passengers", passengerRepository.findAll());
        model.addAttribute("flights", flightRepository.findAll());
        return "tickets/form";
    }

    @PostMapping("/save")
    public String saveTicket(@Valid @ModelAttribute("ticket") Ticket ticket,
                             BindingResult result,
                             @RequestParam("passengerId") Long passengerId,
                             @RequestParam("flightId") Long flightId,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("passengers", passengerRepository.findAll());
            model.addAttribute("flights", flightRepository.findAll());
            return "tickets/form";
        }

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid passenger Id:" + passengerId));
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + flightId));

        ticket.setPassenger(passenger);
        ticket.setFlight(flight);

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
