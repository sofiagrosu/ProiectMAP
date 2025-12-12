package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.repository.AirplaneRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class AirplaneService {
    private final AirplaneRepository repo;
    public AirplaneService(AirplaneRepository repo){ this.repo = repo; }
    public Page<Airplane> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public Airplane findById(Long id){ return repo.findById(id).orElse(null); }
    public Airplane save(Airplane a){ return repo.save(a); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }

    public Airplane findByNumber(int number) {
        return repo.findByNumber(number);
    }
}
