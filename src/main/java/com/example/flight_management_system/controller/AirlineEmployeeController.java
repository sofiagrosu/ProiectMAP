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
    private AirlineEmployeeRepository employeeRepository;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
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

        // 1. Preluăm obiectul existent din DB dacă are ID
        if (employee.getId() != null) {
            AirlineEmployee existing = employeeRepository.findById(employee.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + employee.getId()));
            existing.setName(employee.getName());
            existing.setRole(employee.getRole());
            employeeRepository.save(existing);
        } else {
            // 2. Dacă e nou
            employeeRepository.save(employee);
        }

        return "redirect:/airline-employees";
    }


    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Long id, Model model) {
        AirlineEmployee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airline-employees/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        AirlineEmployee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employeeRepository.delete(employee);
        return "redirect:/airline-employees";
    }

    @GetMapping("/details/{id}")
    public String employeeDetails(@PathVariable("id") Long id, Model model) {
        AirlineEmployee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airline-employees/details";
    }
}
