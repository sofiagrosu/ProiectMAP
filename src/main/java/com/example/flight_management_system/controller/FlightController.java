package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flights/index";
    }

    @GetMapping("/new")
    public String createFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flights/form";
    }

    @PostMapping("/save")
    public String saveFlight(@Valid @ModelAttribute("flight") Flight flight,
                             BindingResult result) {
        if (result.hasErrors()) return "flights/form";
        flightRepository.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/edit/{id}")
    public String editFlightForm(@PathVariable("id") Long id, Model model) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));
        model.addAttribute("flight", flight);
        return "flights/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable("id") Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));
        flightRepository.delete(flight);
        return "redirect:/flights";
    }

    @GetMapping("/details/{id}")
    public String flightDetails(@PathVariable("id") Long id, Model model) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));
        model.addAttribute("flight", flight);
        return "flights/details";
    }
}
