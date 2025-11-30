package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.repository.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping
    public String listPassengers(Model model) {
        model.addAttribute("passengers", passengerRepository.findAll());
        return "passengers/index";
    }

    @GetMapping("/new")
    public String createPassengerForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passengers/form";
    }

    @PostMapping("/save")
    public String savePassenger(@Valid @ModelAttribute("passenger") Passenger passenger,
                                BindingResult result) {
        // JSR-303 Validation check (Requirement 1.6 a)
        if (result.hasErrors()) return "passengers/form";

        // No complex Business Validation implemented here (assuming Name/Currency uniqueness is not required)

        passengerRepository.save(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/edit/{id}")
    public String editPassengerForm(@PathVariable("id") Long id, Model model) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid passenger Id:" + id));
        model.addAttribute("passenger", passenger);
        return "passengers/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePassenger(@PathVariable("id") Long id) {
        // GlobalExceptionHandler will catch DataIntegrityViolationException if relations exist (Requirement 1.6 b & c)
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid passenger Id:" + id));
        passengerRepository.delete(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/details/{id}")
    public String passengerDetails(@PathVariable("id") Long id, Model model) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid passenger Id:" + id));
        model.addAttribute("passenger", passenger);
        return "passengers/details";
    }
}