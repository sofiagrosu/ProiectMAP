package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.AirportEmployee;

import com.example.flight_management_system.service.AirportEmployeeService;
import com.example.flight_management_system.specification.filter.AirportEmployeeFilter;
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
@RequestMapping("/airport-employees")
public class AirportEmployeeController {

    @Autowired
    private AirportEmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(
            @ModelAttribute("filter") AirportEmployeeFilter filter,// pentru filtrare
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<AirportEmployee> employeePage = employeeService.search(filter,pageable);
        model.addAttribute("employees", employeePage.getContent());     // pentru tabel
        model.addAttribute("page", employeePage);                     // pentru paginare (opțional)
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);

        return "airport-employees/index";


    }
    private Sort buildSort(String sortBy, boolean ascending) {
        return switch (sortBy) {
            case "id",
                 "department",
                 "name",
                 "designation",
                 "employeeNumber" ->
                    ascending ? Sort.by(sortBy).ascending()
                            : Sort.by(sortBy).descending();
            default -> Sort.by("id").ascending();
        };
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
        AirportEmployee existingEmployee = employeeService.findByEmployeeNumber(employee.getEmployeeNumber());

        if (existingEmployee != null && !existingEmployee.getId().equals(employee.getId())) {
            result.rejectValue("employeeNumber", "unique", "An employee with this number already exists.");
            return "airport-employees/form";
        }

        employeeService.save(employee);
        return "redirect:/airport-employees";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        AirportEmployee employee = employeeService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airport-employees/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        AirportEmployee employee = employeeService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employeeService.delete(employee.getId());
        return "redirect:/airport-employees";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        AirportEmployee employee = employeeService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "airport-employees/details";
    }
}