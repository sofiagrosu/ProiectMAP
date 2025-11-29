package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Staff;
import com.example.flight_management_system.repository.StaffRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staffs")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staffs", staffRepository.findAll());
        return "staffs/index";
    }

    @GetMapping("/new")
    public String createStaffForm(Model model) {
        model.addAttribute("staff", new Staff() {});
        return "staffs/form";
    }

    @PostMapping("/save")
    public String saveStaff(@Valid @ModelAttribute("staff") Staff staff,
                            BindingResult result) {
        if (result.hasErrors()) return "staffs/form";
        staffRepository.save(staff);
        return "redirect:/staffs";
    }

    @GetMapping("/edit/{id}")
    public String editStaffForm(@PathVariable("id") String id, Model model) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        model.addAttribute("staff", staff);
        return "staffs/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") String id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        staffRepository.delete(staff);
        return "redirect:/staffs";
    }

    @GetMapping("/details/{id}")
    public String staffDetails(@PathVariable("id") String id, Model model) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        model.addAttribute("staff", staff);
        return "staffs/details";
    }
}
