package com.example.flight_management_system.controller;


import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.service.AirplaneService;
import com.example.flight_management_system.service.FlightService;
import com.example.flight_management_system.service.NoticeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    private final AirplaneService airplaneService;
    private final NoticeBoardService noticeBoardService;

    public FlightController(FlightService flightService, AirplaneService airplaneService, NoticeBoardService noticeBoardService) {
        this.flightService = flightService;
        this.airplaneService = airplaneService;
        this.noticeBoardService = noticeBoardService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Flight> flighAll(){
        return flightService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Flight flightId(@PathVariable String id){
        return flightService.findById(id);}

    @GetMapping("/airplane")
    @ResponseBody
    public List<Flight> airplaneAll(){
        return flightService.findAll();
    }
    @GetMapping("/airplane/{id}")
    @ResponseBody
    public Flight airplaneId(@PathVariable String id){
        return flightService.findById(id);}

    @GetMapping("/notices")
    @ResponseBody
    public List<NoticeBoard> noticeAll(){
        return noticeBoardService.findAll();
    }
    @GetMapping("/notices/{id}")
    @ResponseBody
    public NoticeBoard noticeId(@PathVariable String id) {
        return noticeBoardService.findById(id);
    }

}