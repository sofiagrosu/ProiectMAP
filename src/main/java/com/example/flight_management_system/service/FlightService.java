package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService extends CrudService<Flight> {

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        super(flightRepository);
    }
}
