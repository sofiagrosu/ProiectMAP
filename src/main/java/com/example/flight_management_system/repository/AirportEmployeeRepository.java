package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.AirportEmployee;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AirportEmployeeRepository extends InFileRepository<AirportEmployee>  {

    public AirportEmployeeRepository() {
        super("airport_employees.json", AirportEmployee.class);
    }

}
