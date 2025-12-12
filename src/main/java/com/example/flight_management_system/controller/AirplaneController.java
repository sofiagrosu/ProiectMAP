package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Airplane;

import com.example.flight_management_system.service.AirplaneService;
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
@RequestMapping("/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @GetMapping
    public String listAirplanes(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "true") boolean ascending,
        Model model)
        {

            Sort sort = buildSort(sortBy, ascending);
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<Airplane> objectPage = airplaneService.findAll(pageable);
            model.addAttribute("airplanes", objectPage.getContent());     // pentru tabel
            model.addAttribute("page", objectPage);                     // pentru paginare (opțional)
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("ascending", ascending);

            return "airplanes/index";


        }
        private Sort buildSort(String sortBy, boolean ascending) {
            return switch (sortBy) {
                case "id","number"-> ascending ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending();
                default -> Sort.by("id").ascending();
            };
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

        Airplane existingAirplane = airplaneService.findByNumber(airplane.getNumber());

        if (existingAirplane != null && !existingAirplane.getId().equals(airplane.getId())) {
            result.rejectValue("number", "unique", "An airplane with this number already exists.");
            return "airplanes/form";
        }

        airplaneService.save(airplane);
        return "redirect:/airplanes";
    }

    @GetMapping("/edit/{id}")
    public String editAirplaneForm(@PathVariable("id") Long id, Model model) {
        Airplane airplane = airplaneService.findById(id);
             //   .orElseThrow(() -> new IllegalArgumentException("Invalid airplane Id:" + id));
        model.addAttribute("airplane", airplane);
        return "airplanes/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAirplane(@PathVariable("id") Long id) {
        Airplane airplane = airplaneService.findById(id);
            //    .orElseThrow(() -> new IllegalArgumentException("Invalid airplane Id:" + id));
        airplaneService.delete(airplane.getId());
        return "redirect:/airplanes";
    }

    @GetMapping("/details/{id}")
    public String airplaneDetails(@PathVariable("id") Long id, Model model) {
        Airplane airplane = airplaneService.findById(id);
           //     .orElseThrow(() -> new IllegalArgumentException("Invalid airplane Id:" + id));
        model.addAttribute("airplane", airplane);
        return "airplanes/details";
    }
}