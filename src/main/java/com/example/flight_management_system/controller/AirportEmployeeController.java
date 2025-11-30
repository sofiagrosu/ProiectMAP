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
    private AirportEmployeeRepository airportEmployeeRepository;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", airportEmployeeRepository.findAll());
        return "airport-employees/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new AirportEmployee());
        return "airport-employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") AirportEmployee employee,
                               BindingResult result) {

        if (result.hasErrors()) return "airport-employees/form";

        // Business Rule: Check for unique employee number
        AirportEmployee existingEmployee = airportEmployeeRepository.findByEmployeeNumber(employee.getEmployeeNumber());

        if (existingEmployee != null && !existingEmployee.getId().equals(employee.getId())) {
            result.rejectValue("employeeNumber", "unique", "An employee with this number already exists.");
            return "airport-employees/form";
        }

        airportEmployeeRepository.save(employee);
        return "redirect:/airport-employees";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        AirportEmployee employee = airportEmployeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airport-employees/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        AirportEmployee employee = airportEmployeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        airportEmployeeRepository.delete(employee);
        return "redirect:/airport-employees";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        AirportEmployee employee = airportEmployeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airport-employees/details";
    }
}