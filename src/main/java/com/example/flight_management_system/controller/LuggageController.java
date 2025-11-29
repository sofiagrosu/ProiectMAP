package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.model.Status;
import com.example.flight_management_system.repository.LuggageRepository;
import com.example.flight_management_system.repository.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/luggages")
public class LuggageController {

    @Autowired
    private LuggageRepository luggageRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public String listLuggages(Model model) {
        model.addAttribute("luggages", luggageRepository.findAll());
        return "luggages/index";
    }

    @GetMapping("/new")
    public String createLuggageForm(Model model, @RequestParam(required = false) Long ticketId) {
        Luggage luggage = new Luggage();
        if (ticketId != null) {
            luggage.setTicket(ticketRepository.findById(ticketId).orElse(null));
        }

        model.addAttribute("luggage", luggage);
        model.addAttribute("tickets", ticketRepository.findAll());
        model.addAttribute("statuses", Status.values());
        return "luggages/form";
    }

    @PostMapping("/save")
    public String saveLuggage(@Valid @ModelAttribute("luggage") Luggage luggage,
                              @RequestParam("ticketId") Long ticketId,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tickets", ticketRepository.findAll());
            model.addAttribute("statuses", Status.values());
            return "luggages/form";
        }

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + ticketId));

        luggage.setTicket(ticket);
        luggageRepository.save(luggage);

        return "redirect:/luggages"; // Redirecționare la Index
    }

    @GetMapping("/edit/{id}")
    public String editLuggageForm(@PathVariable("id") Long id, Model model) {
        Luggage luggage = luggageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid luggage Id:" + id));
        model.addAttribute("luggage", luggage);
        model.addAttribute("tickets", ticketRepository.findAll());
        model.addAttribute("statuses", Status.values());
        return "luggages/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteLuggage(@PathVariable("id") Long id) {
        Luggage luggage = luggageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid luggage Id:" + id));
        luggageRepository.delete(luggage);

        return "redirect:/luggages"; // Redirecționare la Index
    }

    @GetMapping("/details/{id}")
    public String luggageDetails(@PathVariable("id") Long id, Model model) {
        Luggage luggage = luggageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid luggage Id:" + id));
        model.addAttribute("luggage", luggage);
        return "luggages/details";
    }
}