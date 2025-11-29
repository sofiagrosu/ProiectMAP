package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.repository.FlightAssignmentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
public class FlightAssignmentController {

    @Autowired
    private FlightAssignmentRepository assignmentRepository;

    @GetMapping
    public String listAssignments(Model model) {
        model.addAttribute("assignments", assignmentRepository.findAll());
        return "assignments/index";
    }

    @GetMapping("/new")
    public String createAssignmentForm(Model model) {
        model.addAttribute("assignment", new FlightAssignment());
        return "assignments/form";
    }

    @PostMapping("/save")
    public String saveAssignment(@Valid @ModelAttribute("assignment") FlightAssignment assignment,
                                 BindingResult result) {
        if (result.hasErrors()) return "assignments/form";
        assignmentRepository.save(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/edit/{id}")
    public String editAssignmentForm(@PathVariable("id") String id, Model model) {
        FlightAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid assignment Id:" + id));
        model.addAttribute("assignment", assignment);
        return "assignments/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable("id") String id) {
        FlightAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid assignment Id:" + id));
        assignmentRepository.delete(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/details/{id}")
    public String assignmentDetails(@PathVariable("id") String id, Model model) {
        FlightAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid assignment Id:" + id));
        model.addAttribute("assignment", assignment);
        return "assignments/details";
    }
}
