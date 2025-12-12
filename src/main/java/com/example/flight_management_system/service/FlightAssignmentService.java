package com.example.flight_management_system.service;

import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.repository.FlightAssignmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class FlightAssignmentService {
    private final FlightAssignmentRepository repo;
    public FlightAssignmentService(FlightAssignmentRepository repo){ this.repo = repo; }
    public Page<FlightAssignment> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public FlightAssignment findById(Long id){ return repo.findById(id).orElse(null); }
    public FlightAssignment save(FlightAssignment fa){ return repo.save(fa); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
