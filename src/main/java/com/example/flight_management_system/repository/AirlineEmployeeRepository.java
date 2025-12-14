package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface AirlineEmployeeRepository extends JpaRepository<AirlineEmployee, Long>, JpaSpecificationExecutor<AirlineEmployee> {
    AirlineEmployee findByEmployeeNumber(String employeeNumber);
}
