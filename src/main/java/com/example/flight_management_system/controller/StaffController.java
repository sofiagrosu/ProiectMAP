package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.service.AirlineEmployeeService;
import com.example.flight_management_system.service.AirportEmployeeService;
import com.example.flight_management_system.service.FlightAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final AirlineEmployeeService airlineEmployeeService;
    private final AirportEmployeeService airportEmployeeService;
    private final FlightAssignmentService flightAssignmentService;

    public StaffController(AirlineEmployeeService airlineEmployeeService, AirportEmployeeService airportEmployeeService, FlightAssignmentService flightAssignmentService) {
        this.airlineEmployeeService = airlineEmployeeService;
        this.airportEmployeeService = airportEmployeeService;
        this.flightAssignmentService = flightAssignmentService;
    }

    @GetMapping("/airline")
    @ResponseBody
    public Iterable<AirlineEmployee> airlineAll(){
        return airlineEmployeeService.findAll();
    }
    @GetMapping("/staff/airline/{id}")
    @ResponseBody
    public AirlineEmployee airlineId(@PathVariable String id){
        return airlineEmployeeService.findById(id);
    }
    @GetMapping("/airport")
    @ResponseBody
    public Iterable<AirportEmployee> airportAll(){
        return airportEmployeeService.findAll();
    }
    @GetMapping("/staff/airport/{id}")
    @ResponseBody
    public AirportEmployee airportId(@PathVariable String id){
        return airportEmployeeService.findById(id);
    }
    @GetMapping("/assignments")
    @ResponseBody
    public List<FlightAssignment> assignmentAll(){
        return flightAssignmentService.findAll();
    }
    @GetMapping("/staff/assignment/{id}")
    @ResponseBody
    public FlightAssignment assignmentId(@PathVariable String id){
        return flightAssignmentService.findById(id);
    }



}
