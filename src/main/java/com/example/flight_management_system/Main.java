package com.example.flight_management_system;

import com.example.flight_management_system.controller.FlightController;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.repository.*;
import com.example.flight_management_system.service.AirplaneService;
import com.example.flight_management_system.service.FlightService;
import com.example.flight_management_system.service.NoticeBoardService;

public class Main {
    public static void main(String[] args) {
        FlightService flightService = new FlightService(new FlightRepository());
        FlightAssignmentRepository assignmentRepo = new FlightAssignmentRepository();
        LuggageRepository luggageRepo = new LuggageRepository();

        Flight flight1 = new Flight("F001", "Bucuresti-Londra", "NB001", "A001", "5");
        Flight flight2 = new Flight("F002", "Cluj-Paris", "NB002", "A002", "3");
        Flight flight3 = new Flight("F003", "Timisoara-Roma", "NB003", "A003", "7");

        flightService.save(flight1);
        FlightController fc = new FlightController(flightService,new AirplaneService(new AirplaneRepository()), new NoticeBoardService(new NoticeBoardRepository()));

    }
}
