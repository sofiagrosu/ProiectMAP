package com.example.flight_management_system.controller;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final AirlineEmployeeService airlineEmployeeService;
    private final AirportEmployeeService airportEmployeeService;
    @GetMapping("/airline")
    @ResponseBody
        return airlineEmployeeService.findAll();
    }
    @GetMapping("/staff/airline/{id}")
    @ResponseBody
    }


}
