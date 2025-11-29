package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirlineEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineEmployeeRepository extends JpaRepository<AirlineEmployee, String> {
}
