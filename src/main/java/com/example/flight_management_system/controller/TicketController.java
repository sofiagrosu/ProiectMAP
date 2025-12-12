package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.TicketRepository;
import com.example.flight_management_system.repository.PassengerRepository;
import com.example.flight_management_system.repository.FlightRepository;
import com.example.flight_management_system.service.FlightService;
import com.example.flight_management_system.service.PassengerService;
import com.example.flight_management_system.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private FlightService flightService;

    @GetMapping
    public String listTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Ticket> objectPage = ticketService.findAll(pageable);
        model.addAttribute("tickets", objectPage.getContent());     // pentru tabel
        model.addAttribute("page", objectPage);                     // pentru paginare (opțional)
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);

        return "tickets/index";


    }
    private Sort buildSort(String sortBy, boolean ascending) {
        Sort.Direction dir = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;

        return switch (sortBy) {
            case "id" -> Sort.by(dir, "id");
            case "passenger" -> Sort.by(dir, "passenger.id");
            case "flight" -> Sort.by(dir, "flight.id");
            case "price" -> Sort.by(dir, "price");
            case "seatNumber" -> Sort.by(dir, "seatNumber");

            default -> Sort.by(Sort.Direction.ASC, "id");
        };
    }

    @GetMapping("/new")
    public String createTicketForm(Model model, @RequestParam(required = false) Long flightId, @RequestParam(required = false) Long passengerId) {
        Ticket ticket = new Ticket();
        if (flightId != null) {
            ticket.setFlight(flightService.findById(flightId));
            //.orElse(null));
        }
        if (passengerId != null) {
            ticket.setPassenger(passengerService.findById(passengerId));
            //.orElse(null));
        }

        model.addAttribute("ticket", ticket);
        model.addAttribute("passengers", passengerService.findAll());
        model.addAttribute("flights", flightService.findAll());
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
                ticket.setPassenger(passengerService.findById(passengerId));
                //.orElse(new Passenger()));
                if (ticket.getPassenger().getId() == null) ticket.getPassenger().setId(passengerId);
            }
            if (flightId != null) {
                ticket.setFlight(flightService.findById(flightId));
                //.orElse(new Flight()));
                if (ticket.getFlight().getId() == null) ticket.getFlight().setId(flightId);
            }

            model.addAttribute("passengers", passengerService.findAll());
            model.addAttribute("flights", flightService.findAll());
            return "tickets/form";
        }

        try {
            if (passengerId != null) {
                ticket.setPassenger(passengerService.findById(passengerId));
                //.orElse(null));
            }
            if (flightId != null) {
                ticket.setFlight(flightService.findById(flightId));
                //.orElse(null));
            }


        } catch (Exception e) {
            throw new IllegalArgumentException("Error mapping relationships: " + e.getMessage());
        }

        ticketService.save(ticket);

        return "redirect:/tickets";
    }

    @GetMapping("/edit/{id}")
    public String editTicketForm(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketService.findById(id);

//                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));
        model.addAttribute("ticket", ticket);
        model.addAttribute("passengers", passengerService.findAll());
        model.addAttribute("flights", flightService.findAll());
        return "tickets/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable("id") Long id) {
        Ticket ticket = ticketService.findById(id);
                //.orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));
        ticketService.delete(ticket.getId());

        return "redirect:/tickets";
    }

    @GetMapping("/details/{id}")
    public String ticketDetails(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketService.findById(id);
              //  .orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));
        model.addAttribute("ticket", ticket);
        return "tickets/details";
    }
}