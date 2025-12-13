package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.model.Status;
import com.example.flight_management_system.repository.LuggageRepository;
import com.example.flight_management_system.repository.TicketRepository;
import com.example.flight_management_system.service.LuggageService;
import com.example.flight_management_system.service.TicketService;
import com.example.flight_management_system.specification.filter.LuggageFilter;
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
@RequestMapping("/luggages")
public class LuggageController {

    @Autowired
    private LuggageService luggageService;

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String listLuggages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            @ModelAttribute("filter") LuggageFilter filter,
            Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Luggage> objectPage = luggageService.search(filter,pageable);
        model.addAttribute("luggages", objectPage.getContent());     // pentru tabel
        model.addAttribute("page", objectPage);                     // pentru paginare (opțional)
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);
        model.addAttribute("statuses", Status.values());

        return "luggages/index";


    }
    private Sort buildSort(String sortBy, boolean ascending) {
        Sort.Direction dir = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;

        return switch (sortBy) {
            case "id" -> Sort.by(dir, "id");
            case "status" -> Sort.by(dir, "status");

            // sortare după ticket_id -> trebuie property path, NU nume coloană
            case "ticket" -> Sort.by(dir, "ticket.id");

            default -> Sort.by(Sort.Direction.ASC, "id");
        };
    }
    @GetMapping("/new")
    public String createLuggageForm(Model model, @RequestParam(required = false) Long ticketId) {
        Luggage luggage = new Luggage();
        if (ticketId != null) {
            luggage.setTicket(ticketService.findById(ticketId));
            //.orElse(null));
        }

        model.addAttribute("luggage", luggage);
        model.addAttribute("tickets", ticketService.findAll());
        model.addAttribute("statuses", Status.values());
        return "luggages/form";
    }

    @PostMapping("/save")
    public String saveLuggage(@Valid @ModelAttribute("luggage") Luggage luggage,
                              @RequestParam(value = "ticketId", required = false) Long ticketId,
                              BindingResult result, Model model) {


        if (result.hasErrors()) {
            if (ticketId != null) {
                Ticket selectedTicket = new Ticket();
                selectedTicket.setId(ticketId);
                luggage.setTicket(selectedTicket);
            }

            model.addAttribute("tickets", ticketService.findAll());
            model.addAttribute("statuses", Status.values());
            return "luggages/form";
        }

        try {
            if (ticketId == null) {
                result.rejectValue("ticket", "notnull", "Ticket must be selected");
            }
            if (result.hasErrors()) {
                model.addAttribute("tickets", ticketService.findAll());
                model.addAttribute("statuses", Status.values());
                return "luggages/form";
            }

            Ticket ticket = ticketService.findById(ticketId);
                    //.orElseThrow(() -> new IllegalArgumentException("Ticket not found for ID: " + ticketId));
            luggage.setTicket(ticket);

        } catch (IllegalArgumentException e) {
            throw e;
        }

        luggageService.save(luggage);
        return "redirect:/luggages";
    }

    @GetMapping("/edit/{id}")
    public String editLuggageForm(@PathVariable("id") Long id, Model model) {
        Luggage luggage = luggageService.findById(id);
              //  .orElseThrow(() -> new IllegalArgumentException("Invalid luggage Id:" + id));
        model.addAttribute("luggage", luggage);
        model.addAttribute("tickets", ticketService.findAll());
        model.addAttribute("statuses", Status.values());
        return "luggages/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteLuggage(@PathVariable("id") Long id) {
        Luggage luggage = luggageService.findById(id);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid luggage Id:" + id));
        luggageService.delete(luggage.getId());

        return "redirect:/luggages";
    }

    @GetMapping("/details/{id}")
    public String luggageDetails(@PathVariable("id") Long id, Model model) {
        Luggage luggage = luggageService.findById(id);
              //  .orElseThrow(() -> new IllegalArgumentException("Invalid luggage Id:" + id));
        model.addAttribute("luggage", luggage);
        return "luggages/details";
    }
}