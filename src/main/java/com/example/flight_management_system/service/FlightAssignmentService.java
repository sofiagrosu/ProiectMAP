package com.example.flight_management_system.service;

import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.repository.FlightAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightAssignmentService {
    private final FlightAssignmentRepository flightAssignmentRepository;

    @Autowired
    public FlightAssignmentService(FlightAssignmentRepository flightAssignmentRepository) {
        this.flightAssignmentRepository = flightAssignmentRepository;
    }

    public Iterable<FlightAssignment> findAll(){
        return flightAssignmentRepository.findAll();
    }

    public FlightAssignment findById(String id){
        return flightAssignmentRepository.findById(id);
    }

    public void save(FlightAssignment assignment){
        flightAssignmentRepository.save(assignment);
    }

    public boolean delete(FlightAssignment assignment){
        return flightAssignmentRepository.delete(assignment);
    }
}
