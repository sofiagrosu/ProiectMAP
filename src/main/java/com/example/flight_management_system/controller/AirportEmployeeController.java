package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.repository.AirportEmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airport-employees")
public class AirportEmployeeController {

    @Autowired
    private AirportEmployeeRepository repository;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", repository.findAll());
        return "airport-employees/index";
    }

    @GetMapping("/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new AirportEmployee());
        return "airport-employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") AirportEmployee employee,
                               BindingResult result) {
        if (result.hasErrors()) return "airport-employees/form";
        repository.save(employee);
        return "redirect:/airport-employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") String id, Model model) {
        AirportEmployee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airport-employees/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        AirportEmployee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        repository.delete(employee);
        return "redirect:/airport-employees";
    }

    @GetMapping("/details/{id}")
    public String employeeDetails(@PathVariable("id") String id, Model model) {
        AirportEmployee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airport-employees/details";
    }
}
