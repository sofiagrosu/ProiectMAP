package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/flight-assignments")
public class FlightAssignmentController extends AbstractCrudController<FlightAssignment> {

    public FlightAssignmentController(CrudService<FlightAssignment> service) {
        super(service,
                "/flight-assignments",
                "assignments/index",
                "assignments/form",
                "assignments",
                "assignment",
                FlightAssignment::new);
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("assignment") FlightAssignment assignment) {
        service.save(assignment);
        return "redirect:/flight-assignments";
    }
}
