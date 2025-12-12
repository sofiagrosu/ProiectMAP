package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Role;
import com.example.flight_management_system.repository.AirlineEmployeeRepository;
import com.example.flight_management_system.service.AirlineEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airline-employees")
public class AirlineEmployeeController {

    @Autowired
    private AirlineEmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<AirlineEmployee> employeePage = employeeService.findAll(pageable);
        model.addAttribute("employees", employeePage.getContent());     // pentru tabel
        model.addAttribute("page", employeePage);                     // pentru paginare (opțional)
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);

        return "airline-employees/index";


    }
    private Sort buildSort(String sortBy, boolean ascending) {
        return switch (sortBy) {
            case "id","role", "name", "employeeNumber" , "company" -> ascending ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
            default -> Sort.by("id").ascending();
        };
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

        AirlineEmployee existingEmployee = employeeService.findByEmployeeNumber(employee.getEmployeeNumber());

        if (existingEmployee != null && !existingEmployee.getId().equals(employee.getId())) {
            result.rejectValue("employeeNumber", "unique", "This employee number already exists.");
            model.addAttribute("roles", Role.values());
            return "airline-employees/form";
        }

        employeeService.save(employee);
        return "redirect:/airline-employees";
    }


    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Long id, Model model) {
        AirlineEmployee employee = employeeService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        model.addAttribute("roles", Role.values());
        return "airline-employees/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        AirlineEmployee employee = employeeService.findById(id);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employeeService.delete(employee.getId());
        return "redirect:/airline-employees";
    }

    @GetMapping("/details/{id}")
    public String employeeDetails(@PathVariable("id") Long id, Model model) {
        AirlineEmployee employee = employeeService.findById(id);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airline-employees/details";
    }
}