package com.example.flight_management_system.repository;
import com.example.flight_management_system.model.FlightAssignment;

import java.util.ArrayList;
import java.util.List;

public class FlightAssignmentRepository implements Repository<FlightAssignment> {
    private List<FlightAssignment> flightAssignmentList;
    public FlightAssignmentRepository() {
        this.flightAssignmentList = new ArrayList<>();
    }
    @Override
    public void add(FlightAssignment item) {
        flightAssignmentList.add(item);

    }
    @Override
    public void delete(FlightAssignment item) {
        flightAssignmentList.remove(item);  }
    @Override
    public Iterable<FlightAssignment> findAll() {
        return flightAssignmentList;  }
    @Override
    public FlightAssignment findById(String searchedId) {
        for (FlightAssignment assignment : flightAssignmentList) {
            if (assignment.getId().equals(searchedId)) {
                return assignment;
            }
        }
        return null;
    }

}
