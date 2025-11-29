package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository repo;
    public PassengerService(PassengerRepository repo){ this.repo = repo; }
    public List<Passenger> findAll(){ return repo.findAll(); }
    public Passenger findById(Long id){ return repo.findById(id).orElse(null); }
    public Passenger save(Passenger p){ return repo.save(p); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }
}
