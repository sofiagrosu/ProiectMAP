package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController extends AbstractCrudController<Flight> {
    public FlightController(CrudService<Flight> service) {
        super(service,
                "/flights",
                "flights/index",
                "flights/form",
                "flights",
                "flight",
                Flight::new);
    }

    // (opțional, recomandat) suprascrii create pentru @ModelAttribute clar
    @Override
    @PostMapping
    public String create(@ModelAttribute("flight") Flight flight) {
        service.save(flight);
        return "redirect:/flights";
    }
}