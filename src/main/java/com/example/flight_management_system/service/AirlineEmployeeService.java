package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.repository.AirlineEmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AirlineEmployeeService {
    private final AirlineEmployeeRepository repo;
    public AirlineEmployeeService(AirlineEmployeeRepository repo){ this.repo = repo; }
    public List<AirlineEmployee> findAll(){ return repo.findAll(); }
    public AirlineEmployee findById(String id){ return repo.findById(id).orElse(null); }
    public AirlineEmployee save(AirlineEmployee e){ return repo.save(e); }
    public void delete(String id){ repo.findById(id).ifPresent(repo::delete); }
}
