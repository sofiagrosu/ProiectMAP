package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.repository.AirplaneRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @GetMapping
    public String listAirplanes(Model model) {
        model.addAttribute("airplanes", airplaneRepository.findAll());
        return "airplanes/index";
    }

    @GetMapping("/new")
    public String createAirplaneForm(Model model) {
        model.addAttribute("airplane", new Airplane());
        return "airplanes/form";
    }

    @PostMapping("/save")
    public String saveAirplane(@Valid @ModelAttribute("airplane") Airplane airplane,
                               BindingResult result) {
        if (result.hasErrors()) return "airplanes/form";
        airplaneRepository.save(airplane);
        return "redirect:/airplanes";
    }

    @GetMapping("/edit/{id}")
    public String editAirplaneForm(@PathVariable("id") Long id, Model model) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid airplane Id:" + id));
        model.addAttribute("airplane", airplane);
        return "airplanes/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAirplane(@PathVariable("id") Long id) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid airplane Id:" + id));
        airplaneRepository.delete(airplane);
        return "redirect:/airplanes";
    }

    @GetMapping("/details/{id}")
    public String airplaneDetails(@PathVariable("id") Long id, Model model) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid airplane Id:" + id));
        model.addAttribute("airplane", airplane);
        return "airplanes/details";
    }
}
