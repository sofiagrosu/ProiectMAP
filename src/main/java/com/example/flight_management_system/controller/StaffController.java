package com.example.flight_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final AirlineEmployeeService airlineEmployeeService;
    private final AirportEmployeeService airportEmployeeService;
    private final FlightAssignmentService flightAssignmentService;
    @GetMapping("/airline")
    @ResponseBody
    public String airlineAll(){
        return airlineEmployeeService.findAll();
    }
    @GetMapping("/staff/airline/{id}")
    @ResponseBody
    public String airlineId(@PathVariable int id){
        return airlineEmployeeService.findById(id);
    }
    @GetMapping("/airport")
    @ResponseBody
    public String airportAll(){
        return airportEmployeeService.findAll();
    }
    @GetMapping("/staff/airport/{id}")
    @ResponseBody
    public String airlineId(@PathVariable int id){
        return airportEmployeeService.findById(id);
    }
    @GetMapping("/assignments")
    @ResponseBody
    public String assignmentAll(){
        return flightAssignmentService.findAll();
    }
    @GetMapping("/staff/assignment/{id}")
    @ResponseBody
    public String airlineId(@PathVariable int id){
        return flightAssignmentService.findById(id);
    }



}
