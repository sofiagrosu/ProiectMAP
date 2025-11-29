package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.repository.AirplaneRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AirplaneService {
    private final AirplaneRepository repo;
    public AirplaneService(AirplaneRepository repo){ this.repo = repo; }
    public List<Airplane> findAll(){ return repo.findAll(); }
    public Airplane findById(String id){ return repo.findById(id).orElse(null); }
    public Airplane save(Airplane a){ return repo.save(a); }
    public void delete(String id){ repo.findById(id).ifPresent(repo::delete); }
}
