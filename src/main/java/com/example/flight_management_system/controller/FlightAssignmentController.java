package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Staff;
import com.example.flight_management_system.repository.FlightAssignmentRepository;
import com.example.flight_management_system.repository.FlightRepository;
import com.example.flight_management_system.repository.StaffRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assignments")
public class FlightAssignmentController {

    private final FlightAssignmentRepository assignmentRepo;
    private final FlightRepository flightRepo;
    private final StaffRepository staffRepo;

    public FlightAssignmentController(FlightAssignmentRepository assignmentRepo,
                                      FlightRepository flightRepo,
                                      StaffRepository staffRepo) {
        this.assignmentRepo = assignmentRepo;
        this.flightRepo = flightRepo;
        this.staffRepo = staffRepo;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("assignments", assignmentRepo.findAll());
        return "assignments/index";
    }

    @GetMapping("/new")
    public String newAssignment(Model model) {
        model.addAttribute("assignment", new FlightAssignment());
        model.addAttribute("flights", flightRepo.findAll());
        model.addAttribute("staff", staffRepo.findAll());
        return "assignments/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute FlightAssignment assignment) {
        assignmentRepo.save(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        FlightAssignment assignment = assignmentRepo.findById(id).orElse(null);
        model.addAttribute("assignment", assignment);
        model.addAttribute("flights", flightRepo.findAll());
        model.addAttribute("staff", staffRepo.findAll());
        return "assignments/form";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        FlightAssignment assignment = assignmentRepo.findById(id).orElse(null);
        model.addAttribute("assignment", assignment);
        return "assignments/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        assignmentRepo.deleteById(id);
        return "redirect:/assignments";
    }
}
