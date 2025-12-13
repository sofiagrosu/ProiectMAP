package com.example.flight_management_system.service;

import com.example.flight_management_system.specification.FlightSpecifications;
import com.example.flight_management_system.specification.filter.FlightFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository repo;
    public FlightService(FlightRepository repo){ this.repo = repo; }
    public Page<Flight> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public Flight findById(Long id){ return repo.findById(id).orElse(null); }
    public Flight save(Flight f){ return repo.save(f); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }

    public Optional<Flight> findByName(String name) {
       return  repo.findByName(name);
    }

    public List<Flight> findAll() {
        return repo.findAll();
    }
    public Page<Flight> search(FlightFilter filter, Pageable pageable) {
        return repo.findAll(FlightSpecifications.withFilter(filter), pageable);
    }

}
