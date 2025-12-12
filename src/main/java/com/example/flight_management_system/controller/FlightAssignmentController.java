package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Staff;
import com.example.flight_management_system.repository.FlightAssignmentRepository;
import com.example.flight_management_system.repository.FlightRepository;
import com.example.flight_management_system.repository.StaffRepository;
import com.example.flight_management_system.service.FlightAssignmentService;
import com.example.flight_management_system.service.FlightService;
import com.example.flight_management_system.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/assignments")
public class FlightAssignmentController {

    private final FlightAssignmentService assignementService;
    private final FlightService flightService;
    private final StaffService staffService;

    public FlightAssignmentController(FlightAssignmentService assignementService,
                                      FlightService flightService,
                                      StaffService staffService) {
        this.assignementService = assignementService;
        this.flightService = flightService;
        this.staffService = staffService;
    }

    @GetMapping
    public String listAssignments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<FlightAssignment> objectPage = assignementService.findAll(pageable);
        model.addAttribute("assignments", objectPage.getContent());     // pentru tabel
        model.addAttribute("page", objectPage);                     // pentru paginare (opțional)
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);

        return "assignments/index";


    }
    private Sort buildSort(String sortBy, boolean ascending) {
        Sort.Direction dir = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;

        return switch (sortBy) {
            case "id" -> Sort.by(dir, "id");
            case "flight" -> Sort.by(dir, "flight.name");
            case "staff" -> Sort.by(dir, "staff.name");
            case "assignmentDate" -> Sort.by(dir, "assignmentDate");
            default -> Sort.by(Sort.Direction.ASC, "id");

        };
    }

    @GetMapping("/new")
    public String newAssignment(Model model, @RequestParam(required = false) Long flightId) {
        FlightAssignment assignment = new FlightAssignment();
        if (flightId != null) {
         //   assignment.setFlight(flightService.findById(flightId).orElse(null));
        }

        model.addAttribute("assignment", assignment);
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("staff", staffService.findAll());
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

            model.addAttribute("flights", flightService.findAll());
            model.addAttribute("staff", staffService.findAll());

            if (flightId != null && flightId > 0) {
              //  assignment.setFlight(flightService.findById(flightId).orElse(null));
            }
            if (staffId != null && staffId > 0) {
               // assignment.setStaff(staffService.findById(staffId).orElse(null));
            }

            return "assignments/form";
        }

       Flight flight = flightService.findById(flightId);
               //.orElseThrow(() -> new IllegalArgumentException("Invalid Flight Id"));
        Staff staff = staffService.findById(staffId);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid Staff Id"));

        assignment.setFlight(flight);
        assignment.setStaff(staff);

        assignementService.save(assignment);

        return "redirect:/assignments";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        FlightAssignment assignment = assignementService.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Invalid Assignment Id"));
        model.addAttribute("assignment", assignment);
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("staff", staffService.findAll());
        return "assignments/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        FlightAssignment assignment = assignementService.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Invalid Assignment Id"));
        assignementService.deleteById(id);

        return "redirect:/assignments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        FlightAssignment assignment = assignementService.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Invalid Assignment Id"));
        model.addAttribute("assignment", assignment);
        return "assignments/details";
    }
}