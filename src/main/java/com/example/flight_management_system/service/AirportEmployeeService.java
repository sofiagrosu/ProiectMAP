package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.repository.AirportEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportEmployeeService extends CrudService<AirportEmployee> {

    @Autowired
    public AirportEmployeeService(AirportEmployeeRepository airportEmployeeRepository) {
        super(airportEmployeeRepository);
    }
}
