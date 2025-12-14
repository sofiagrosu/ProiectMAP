package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.repository.AirportEmployeeRepository;
import com.example.flight_management_system.specification.AirportEmployeeSpecifications;
import com.example.flight_management_system.specification.filter.AirportEmployeeFilter;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class AirportEmployeeService {
    private final AirportEmployeeRepository repo;
    public AirportEmployeeService(AirportEmployeeRepository repo){ this.repo = repo; }
    public Page<AirportEmployee> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public AirportEmployee findById(Long id){ return repo.findById(id).orElse(null); }
    public AirportEmployee save(AirportEmployee e){ return repo.save(e); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }

    public AirportEmployee findByEmployeeNumber(String employeeNumber) {
        return repo.findByEmployeeNumber(employeeNumber);
    }
    public Page<AirportEmployee> search(AirportEmployeeFilter filter, Pageable pageable){
        return repo.findAll(AirportEmployeeSpecifications.withFilter(filter),pageable);
    }
}
