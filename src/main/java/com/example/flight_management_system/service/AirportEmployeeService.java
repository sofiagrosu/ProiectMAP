package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.repository.AirportEmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AirportEmployeeService {
    private final AirportEmployeeRepository repo;
    public AirportEmployeeService(AirportEmployeeRepository repo){ this.repo = repo; }
    public List<AirportEmployee> findAll(){ return repo.findAll(); }
    public AirportEmployee findById(Long id){ return repo.findById(id).orElse(null); }
    public AirportEmployee save(AirportEmployee e){ return repo.save(e); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }
}
