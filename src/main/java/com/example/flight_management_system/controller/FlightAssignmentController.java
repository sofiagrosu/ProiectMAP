package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Staff;
import com.example.flight_management_system.repository.FlightAssignmentRepository;
import com.example.flight_management_system.repository.FlightRepository;
import com.example.flight_management_system.repository.StaffRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

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
    public String newAssignment(Model model, @RequestParam(required = false) Long flightId) {
        FlightAssignment assignment = new FlightAssignment();
        if (flightId != null) {
            assignment.setFlight(flightRepo.findById(flightId).orElse(null));
        }

        model.addAttribute("assignment", assignment);
        model.addAttribute("flights", flightRepo.findAll());
        model.addAttribute("staff", staffRepo.findAll());
        return "assignments/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("assignment") FlightAssignment assignment,
                       BindingResult result,
                       @RequestParam(value = "flightId", required = false) Long flightId,
                       @RequestParam(value = "staffId", required = false) Long staffId,
                       Model model) {

        if (flightId == null || flightId <= 0) {
            result.rejectValue("flight", "NotNull", "Flight must be selected.");
        }
        if (staffId == null || staffId <= 0) {
            result.rejectValue("staff", "NotNull", "Staff member must be selected.");
        }

        if (result.hasErrors()) {

            model.addAttribute("flights", flightRepo.findAll());
            model.addAttribute("staff", staffRepo.findAll());

            if (flightId != null && flightId > 0) {
                assignment.setFlight(flightRepo.findById(flightId).orElse(null));
            }
            if (staffId != null && staffId > 0) {
                assignment.setStaff(staffRepo.findById(staffId).orElse(null));
            }

            return "assignments/form";
        }

        Flight flight = flightRepo.findById(flightId).orElseThrow(() -> new IllegalArgumentException("Invalid Flight Id"));
        Staff staff = staffRepo.findById(staffId).orElseThrow(() -> new IllegalArgumentException("Invalid Staff Id"));

        assignment.setFlight(flight);
        assignment.setStaff(staff);

        assignmentRepo.save(assignment);

        return "redirect:/assignments";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        FlightAssignment assignment = assignmentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Assignment Id"));
        model.addAttribute("assignment", assignment);
        model.addAttribute("flights", flightRepo.findAll());
        model.addAttribute("staff", staffRepo.findAll());
        return "assignments/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        FlightAssignment assignment = assignmentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Assignment Id"));
        assignmentRepo.deleteById(id);

        return "redirect:/assignments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        FlightAssignment assignment = assignmentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Assignment Id"));
        model.addAttribute("assignment", assignment);
        return "assignments/details";
    }
}