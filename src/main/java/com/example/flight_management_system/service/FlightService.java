package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Flight findById(String id) {
        return flightRepository.findById(id);
    }

    public boolean delete(Flight flight) {
        return flightRepository.delete(flight);
    }

    public void save(Flight flight){
        flightRepository.save(flight);
    }
}
