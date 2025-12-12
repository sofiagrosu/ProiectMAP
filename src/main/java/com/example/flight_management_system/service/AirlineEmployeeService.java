package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.repository.AirlineEmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AirlineEmployeeService {
    private final AirlineEmployeeRepository repo;
    public AirlineEmployeeService(AirlineEmployeeRepository repo){ this.repo = repo; }
    public Page<AirlineEmployee> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public AirlineEmployee findById(Long id){ return repo.findById(id).orElse(null); }
    public AirlineEmployee save(AirlineEmployee e){ return repo.save(e); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }

    public AirlineEmployee findByEmployeeNumber(String employeeNumber) {
       return repo.findByEmployeeNumber(employeeNumber);
    }
}
