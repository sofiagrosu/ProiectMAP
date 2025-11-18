package com.example.flight_management_system.repository;
import com.example.flight_management_system.model.FlightAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface FlightAssignmentRepository extends JpaRepository<FlightAssignment,String> {


}
