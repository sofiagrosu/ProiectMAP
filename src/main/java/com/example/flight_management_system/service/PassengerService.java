package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class PassengerService {
    private final PassengerRepository repo;
    public PassengerService(PassengerRepository repo){ this.repo = repo; }
    public Page<Passenger> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public Passenger findById(Long id){ return repo.findById(id).orElse(null); }
    public Passenger save(Passenger p){ return repo.save(p); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }

    public List<Passenger> findAll() {
        return repo.findAll();
    }
}
