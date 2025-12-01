package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.FlightRepository;
import com.example.flight_management_system.repository.AirplaneRepository;
import com.example.flight_management_system.repository.NoticeBoardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flights/index";
    }

    @GetMapping("/new")
    public String createFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("noticeboards", noticeBoardRepository.findAll());
        return "flights/form";
    }

    @PostMapping("/save")
    public String saveFlight(@Valid @ModelAttribute("flight") Flight flight,
                             BindingResult result,
                             @RequestParam(value = "airplaneId", required = false) Long airplaneId,
                             @RequestParam(value = "noticeBoardId", required = false) Long noticeBoardId,
                             Model model) {


        Optional<Flight> existingFlightOpt = flightRepository.findByName(flight.getName());

        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();
            if (flight.getId() == null || !flight.getId().equals(existingFlight.getId())) {
                result.rejectValue("name", "name.duplicate", "A flight with this name already exists. It must be unique.");
            }
        }

        if (airplaneId == null) {
            result.rejectValue("airplane", "notnull", "Airplane must be selected");
        }
        if (noticeBoardId == null) {
            result.rejectValue("noticeBoard", "notnull", "Notice Board must be selected");
        }

        if (result.hasErrors()) {
            if (airplaneId != null) {
                flight.setAirplane(new com.example.flight_management_system.model.Airplane((int)(long)airplaneId));
            }
            if (noticeBoardId != null) {
                flight.setNoticeBoard(new com.example.flight_management_system.model.NoticeBoard());
                flight.getNoticeBoard().setId(noticeBoardId);
            }

            model.addAttribute("airplanes", airplaneRepository.findAll());
            model.addAttribute("noticeboards", noticeBoardRepository.findAll());
            return "flights/form";
        }

        try {
            flight.setAirplane(airplaneRepository.findById(airplaneId)
                    .orElseThrow(() -> new IllegalArgumentException("Airplane not found for ID: " + airplaneId)));

            flight.setNoticeBoard(noticeBoardRepository.findById(noticeBoardId)
                    .orElseThrow(() -> new IllegalArgumentException("Notice Board not found for ID: " + noticeBoardId)));

        } catch (IllegalArgumentException e) {
            throw e;
        }

        flightRepository.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/edit/{id}")
    public String editFlightForm(@PathVariable("id") Long id, Model model) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));
        model.addAttribute("flight", flight);
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("noticeboards", noticeBoardRepository.findAll());
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