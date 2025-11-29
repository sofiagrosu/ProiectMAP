package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirportEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportEmployeeRepository extends JpaRepository<AirportEmployee, Long> {
}
