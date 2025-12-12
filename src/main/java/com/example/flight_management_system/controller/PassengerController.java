package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.repository.PassengerRepository;
import com.example.flight_management_system.service.PassengerService;
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
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public String listPassengers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Passenger> objectPage = passengerService.findAll(pageable);
        model.addAttribute("passengers", objectPage.getContent());     // pentru tabel
        model.addAttribute("page", objectPage);                     // pentru paginare (opțional)
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);

        return "passengers/index";


    }
    private Sort buildSort(String sortBy, boolean ascending) {
        Sort.Direction dir = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;

        return switch (sortBy) {
            case "id" -> Sort.by(dir, "id");
            case "name" -> Sort.by(dir, "name");
            case "currency" -> Sort.by(dir, "currency");
            case "isCheckedIn" -> Sort.by(dir, "isCheckedIn");

            default -> Sort.by(Sort.Direction.ASC, "id");
        };
    }

    @GetMapping("/new")
    public String createPassengerForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passengers/form";
    }

    @PostMapping("/save")
    public String savePassenger(@Valid @ModelAttribute("passenger") Passenger passenger,
                                BindingResult result) {
        if (result.hasErrors()) return "passengers/form";


        passengerService.save(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/edit/{id}")
    public String editPassengerForm(@PathVariable("id") Long id, Model model) {
        Passenger passenger = passengerService.findById(id);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid passenger Id:" + id));
        model.addAttribute("passenger", passenger);
        return "passengers/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePassenger(@PathVariable("id") Long id) {
        Passenger passenger = passengerService.findById(id);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid passenger Id:" + id));
        passengerService.delete(passenger.getId());
        return "redirect:/passengers";
    }

    @GetMapping("/details/{id}")
    public String passengerDetails(@PathVariable("id") Long id, Model model) {
        Passenger passenger = passengerService.findById(id);
              //  .orElseThrow(() -> new IllegalArgumentException("Invalid passenger Id:" + id));
        model.addAttribute("passenger", passenger);
        return "passengers/details";
    }
}