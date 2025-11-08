package com.example.flight_management_system.repository;
import org.springframework.stereotype.Repository;
import com.example.flight_management_system.model.AirlineEmployee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirlineEmployeeRepository extends InFileRepository<AirlineEmployee> {
    public AirlineEmployeeRepository() {
        super("airline_employees.json", AirlineEmployee.class);
    }

}