package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airport-employees")
public class AirportEmployeeController extends AbstractCrudController<AirportEmployee> {

    public AirportEmployeeController(CrudService<AirportEmployee> service) {
        super(service,
                "/airport-employees",
                "airport-employees/index",
                "airport-employees/form",
                "airportEmployees",
                "airportEmployee",
                AirportEmployee::new);
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("airportEmployee") AirportEmployee employee) {
        service.save(employee);
        return "redirect:/airport-employees";
    }
}
