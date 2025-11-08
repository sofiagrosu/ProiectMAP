package com.example.flight_management_system.controller;

import com.example.flight_management_system.controller.AbstractCrudController;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/passengers")
public class PassengerController extends AbstractCrudController<Passenger> {

    public PassengerController(CrudService<Passenger> service) {
        super(service,
                "/passengers",
                "passengers/index",
                "passengers/form",
                "passengers",
                "passenger",
                Passenger::new);
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("passenger") Passenger passenger) {
        service.save(passenger);
        return "redirect:/passengers";
    }
}
