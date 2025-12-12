package com.example.flight_management_system.controller;
import com.example.flight_management_system.service.FlightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.FlightRepository;
import com.example.flight_management_system.repository.AirplaneRepository;
import com.example.flight_management_system.repository.NoticeBoardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private AirplaneRepository airplaneRepository;
    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

//    @GetMapping
//    public String listFlights(Model model) {
//        model.addAttribute("flights", flightService.findAll());
//        return "flights/index";
//    }
    @GetMapping
    public String getAllFlights(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "true") boolean ascending,
        Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
    Pageable pageable = PageRequest.of(page, size, sort);
        Page<Flight> flightsPage = flightService.findAll(pageable);
        model.addAttribute("flights", flightsPage.getContent());     // pentru tabel
        model.addAttribute("page", flightsPage);                     // pentru paginare (opțional)
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);

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
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("noticeboards", noticeBoardRepository.findAll());
        return "flights/form";
    }

    @PostMapping("/save")
    public String saveFlight(@Valid @ModelAttribute("flight") Flight flight,
                             BindingResult result,  //colecteaza erorile de validare
                             @RequestParam(value = "airplaneId", required = false) Long airplaneId,
                             @RequestParam(value = "noticeBoardId", required = false) Long noticeBoardId,
                             Model model) {


        Optional<Flight> existingFlightOpt = flightService.findByName(flight.getName());

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

        flightService.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/edit/{id}")
    public String editFlightForm(@PathVariable("id") Long id, Model model) {
        Flight flight = flightService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));
        model.addAttribute("flight", flight);
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("noticeboards", noticeBoardRepository.findAll());
        return "flights/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable("id") Long id) {
        Flight flight = flightService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));
        flightService.delete(flight.getId());
        return "redirect:/flights";
    }

    @GetMapping("/details/{id}")
    public String flightDetails(@PathVariable("id") Long id, Model model) {
        Flight flight = flightService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));
        model.addAttribute("flight", flight);
        return "flights/details";
    }
}