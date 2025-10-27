package com.example.flight_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final AirlineEmployeeService airlineEmployeeService;
    private final AirportEmployeeService airportEmployeeService;
    @GetMapping("/airline")
    @ResponseBody
    public String airline(){
        return airlineEmployeeService.findAll();
    }
    @GetMapping("/staff/airline/{id}")
    @ResponseBody
    public String airline(){
        return AirlineEmployeeService.findById(id);
    }


}
