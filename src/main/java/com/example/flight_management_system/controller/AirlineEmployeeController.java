package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airline-employees")
public class AirlineEmployeeController extends AbstractCrudController<AirlineEmployee> {

    public AirlineEmployeeController(CrudService<AirlineEmployee> service) {
        super(service,
                "/airline-employees",         // basePath (redirect)
                "airline-employees/index",    // list view
                "airline-employees/form",     // form view
                "airlineEmployees",           // list model key
                "airlineEmployee",            // form model key
                AirlineEmployee::new);        // factory
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("airlineEmployee") AirlineEmployee employee) {
        service.save(employee);
        return "redirect:/airline-employees";
    }
}
