package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportEmployeeRepository extends JpaRepository<AirportEmployee, Long>, JpaSpecificationExecutor<AirportEmployee> {
    AirportEmployee findByEmployeeNumber(String employeeNumber);
}
