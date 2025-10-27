package com.example.flight_management_system.repository;
import com.example.flight_management_system.model.FlightAssignment;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class FlightAssignmentRepository implements GenericRepository<FlightAssignment> {
    private List<FlightAssignment> flightAssignmentList;
    public FlightAssignmentRepository() {
        this.flightAssignmentList = new ArrayList<>();
    }
    @Override
    public void save(FlightAssignment item) {
        flightAssignmentList.add(item);

    }
    @Override
    public boolean delete(FlightAssignment item) {
        return flightAssignmentList.remove(item);  }
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
