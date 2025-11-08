package com.example.flight_management_system.repository;
import com.example.flight_management_system.model.FlightAssignment;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class FlightAssignmentRepository extends  InFileRepository<FlightAssignment> {

    public FlightAssignmentRepository() {
        super("flight_assignments.json", FlightAssignment.class);
    }
}
