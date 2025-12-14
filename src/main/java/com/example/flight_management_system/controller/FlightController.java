package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.service.AirplaneService;
import com.example.flight_management_system.service.FlightService;
import com.example.flight_management_system.service.NoticeBoardService;
import com.example.flight_management_system.specification.filter.FlightFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private NoticeBoardService noticeBoardService;


    @Autowired
    private com.example.flight_management_system.repository.AirplaneRepository repo;

    @Autowired
    private com.example.flight_management_system.repository.NoticeBoardRepository repo1;

    @GetMapping
    public String getAllFlights(
            @ModelAttribute("filter") FlightFilter filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            Model model
    ) {
        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Flight> flightsPage = flightService.search(filter, pageable);
        model.addAttribute("flights", flightsPage.getContent());
        model.addAttribute("page", flightsPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);

        model.addAttribute("airplanes", airplaneService.findAll());
        model.addAttribute("noticeboards", noticeBoardService.findAll());

        return "flights/index";
    }

    private Sort buildSort(String sortBy, boolean ascending) {
        return switch (sortBy) {
            case "id", "name", "gateNumber" -> ascending ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
            default -> Sort.by("id").ascending();
        };
    }

    @GetMapping("/new")
    public String createFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airplanes", airplaneService.findAll());
        model.addAttribute("noticeboards", noticeBoardService.findAll());
        return "flights/form";
    }

    @PostMapping("/save")
    public String saveFlight(
            @Valid @ModelAttribute("flight") Flight flight,
            BindingResult result,
            // primește STRING ca să poți distinge: gol vs nenumeric
            @RequestParam(value = "airplaneId", required = false) String airplaneIdStr,
            @RequestParam(value = "noticeBoardId", required = false) String noticeBoardIdStr,
            Model model
    ) {

        // ===== 1) Unicitate nume (păstrat) =====
        Optional<Flight> existingFlightOpt = flightService.findByName(flight.getName());
        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();
            if (flight.getId() == null || !flight.getId().equals(existingFlight.getId())) {
                result.rejectValue("name", "name.duplicate",
                        "A flight with this name already exists. It must be unique.");
            }
        }

        // ===== 2) Validare & setare Airplane după ID =====
        Long airplaneId = null;

        if (airplaneIdStr == null || airplaneIdStr.isBlank()) {
            result.rejectValue("airplane", "notnull", "Airplane must be selected");
        } else {
            try {
                airplaneId = Long.parseLong(airplaneIdStr.trim());
                var apOpt = repo.findById(airplaneId);
                if (apOpt.isEmpty()) {
                    result.rejectValue("airplane", "airplane.notfound",
                            "Airplane not found for ID: " + airplaneId);
                } else {
                    flight.setAirplane(apOpt.get());
                }
            } catch (NumberFormatException e) {
                result.rejectValue("airplane", "airplane.invalid", "Airplane ID must be a number");
            }
        }

        // ===== 3) Validare & setare NoticeBoard după ID =====
        Long noticeBoardId = null;

        if (noticeBoardIdStr == null || noticeBoardIdStr.isBlank()) {
            result.rejectValue("noticeBoard", "notnull", "Notice Board must be selected");
        } else {
            try {
                noticeBoardId = Long.parseLong(noticeBoardIdStr.trim());
                var nbOpt = repo1.findById(noticeBoardId);
                if (nbOpt.isEmpty()) {
                    result.rejectValue("noticeBoard", "noticeBoard.notfound",
                            "Notice Board not found for ID: " + noticeBoardId);
                } else {
                    flight.setNoticeBoard(nbOpt.get());
                }
            } catch (NumberFormatException e) {
                result.rejectValue("noticeBoard", "noticeBoard.invalid", "Notice Board ID must be a number");
            }
        }

        // ===== 4) Dacă sunt erori, rămâi în form =====
        if (result.hasErrors()) {
            // păstrează ce ai nevoie în model (ca înainte)
            model.addAttribute("airplanes", airplaneService.findAll());
            model.addAttribute("noticeboards", noticeBoardService.findAll());
            return "flights/form";
        }

        // ===== 5) Save =====
        flightService.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/edit/{id}")
    public String editFlightForm(@PathVariable("id") Long id, Model model) {
        Flight flight = flightService.findById(id);
        model.addAttribute("flight", flight);
        model.addAttribute("airplanes", airplaneService.findAll());
        model.addAttribute("noticeboards", noticeBoardService.findAll());
        return "flights/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable("id") Long id) {
        Flight flight = flightService.findById(id);
        flightService.delete(flight.getId());
        return "redirect:/flights";
    }

    @GetMapping("/details/{id}")
    public String flightDetails(@PathVariable("id") Long id, Model model) {
        Flight flight = flightService.findById(id);
        model.addAttribute("flight", flight);
        return "flights/details";
    }
}
