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
import com.example.flight_management_system.specification.filter.TicketFilter;
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
            @ModelAttribute("filter") TicketFilter filter,
            Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Ticket> objectPage = ticketService.search(filter,pageable);
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

        Passenger passenger = null;
        Flight flight = null;

        // 1. Validate Passenger existence
        if (passengerId != null) {
            passenger = passengerService.findById(passengerId);
            if (passenger == null) {
                result.rejectValue("passenger", "error.ticket.passenger.notfound",
                        "Passenger with ID " + passengerId + " does not exist. Please enter a valid ID.");
            }
        }

        // 2. Validate Flight existence
        if (flightId != null) {
            flight = flightService.findById(flightId);
            if (flight == null) {
                result.rejectValue("flight", "error.ticket.flight.notfound",
                        "Flight with ID " + flightId + " does not exist. Please enter a valid ID.");
            }
        }


        // 3. Check for validation errors (@Valid or custom existence errors)
        if (result.hasErrors()) {
            // Re-set entities (or placeholders) to maintain form state
            if (passenger != null) {
                ticket.setPassenger(passenger);
            } else if (passengerId != null) {
                // Set a temporary Passenger with the invalid ID
                ticket.setPassenger(new Passenger());
                ticket.getPassenger().setId(passengerId);
            }

            if (flight != null) {
                ticket.setFlight(flight);
            } else if (flightId != null) {
                // Set a temporary Flight with the invalid ID
                ticket.setFlight(new Flight());
                ticket.getFlight().setId(flightId);
            }


            // Repopulate model for dropdowns
            model.addAttribute("passengers", passengerService.findAll());
            model.addAttribute("flights", flightService.findAll());
            return "tickets/form";
        }

        // 4. If no errors, set entities and save
        try {
            // Set the validated entities
            ticket.setPassenger(passenger);
            ticket.setFlight(flight);

        } catch (Exception e) {
            // Handle unexpected errors (e.g., mapping errors or Service exceptions)
            result.reject("global.error", "An unexpected error occurred while saving the ticket: " + e.getMessage());
            model.addAttribute("passengers", passengerService.findAll());
            model.addAttribute("flights", flightService.findAll());
            return "tickets/form";
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