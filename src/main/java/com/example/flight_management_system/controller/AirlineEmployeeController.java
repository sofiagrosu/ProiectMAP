package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.repository.AirlineEmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airline-employees")
public class AirlineEmployeeController {

    @Autowired
    private AirlineEmployeeRepository repository;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", repository.findAll());
        return "airline-employees/index";
    }

    @GetMapping("/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new AirlineEmployee());
        return "airline-employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") AirlineEmployee employee,
                               BindingResult result) {
        if (result.hasErrors()) return "airline-employees/form";
        repository.save(employee);
        return "redirect:/airline-employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") String id, Model model) {
        AirlineEmployee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airline-employees/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        AirlineEmployee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        repository.delete(employee);
        return "redirect:/airline-employees";
    }

    @GetMapping("/details/{id}")
    public String employeeDetails(@PathVariable("id") String id, Model model) {
        AirlineEmployee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airline-employees/details";
    }
}
