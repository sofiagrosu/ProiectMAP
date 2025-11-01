package com.example.flight_management_system.service;

import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.repository.FlightAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightAssignmentService extends CrudService<FlightAssignment> {

    @Autowired
    public FlightAssignmentService(FlightAssignmentRepository flightAssignmentRepository) {
        super(flightAssignmentRepository);
    }
}
