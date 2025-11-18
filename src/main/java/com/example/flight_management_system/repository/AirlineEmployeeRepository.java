package com.example.flight_management_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.flight_management_system.model.AirlineEmployee;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AirlineEmployeeRepository extends JpaRepository<AirlineEmployee, String> {

}