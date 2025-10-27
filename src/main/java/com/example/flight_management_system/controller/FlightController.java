package com.example.flight_management_system.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    private final AirplaneService airplaneService;
    private final NoticeBoardService noticeBoardService;

    @GetMapping("/all")
    @ResponseBody
    public String flighAll(){
        return flightService.findAll();
    }
    @GetMapping("/{id}")
    @ResponseBody
    public String flightId(@PathVariable int id){
        return flightService.findById(id);}

    @GetMapping("/airplane")
    @ResponseBody
    public String airplaneAll(){
        return flightService.findAll();
    }
    @GetMapping("/airplane/{id}")
    @ResponseBody
    public String airplaneId(@PathVariable int id){
        return flightService.findById(id);}

    @GetMapping("/notices")
    @ResponseBody
    public String noticeAll(){
        return noticeBoardService.findAll();
    }
    @GetMapping("/notices/{id}")
    @ResponseBody
    public String noticeId(@PathVariable int id) {
        return noticeBoardService.findById(id);
    }

    }
}
