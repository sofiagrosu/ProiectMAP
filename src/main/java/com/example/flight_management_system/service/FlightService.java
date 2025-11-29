package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository repo;
    public FlightService(FlightRepository repo){ this.repo = repo; }
    public List<Flight> findAll(){ return repo.findAll(); }
    public Flight findById(String id){ return repo.findById(id).orElse(null); }
    public Flight save(Flight f){ return repo.save(f); }
    public void delete(String id){ repo.findById(id).ifPresent(repo::delete); }
}
