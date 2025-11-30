package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Role;
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
        model.addAttribute("roles", Role.values());
        return "airline-employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") AirlineEmployee employee,
                               BindingResult result,
                               Model model) {

        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "airline-employees/form";
        }

        AirlineEmployee existingEmployee = employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber());

        if (existingEmployee != null && !existingEmployee.getId().equals(employee.getId())) {
            result.rejectValue("employeeNumber", "unique", "This employee number already exists.");
            model.addAttribute("roles", Role.values());
            return "airline-employees/form";
        }

        employeeRepository.save(employee);
        return "redirect:/airline-employees";
    }


    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Long id, Model model) {
        AirlineEmployee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        model.addAttribute("roles", Role.values());
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