package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.repository.AirlineEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineEmployeeService extends CrudService<AirlineEmployee> {

    @Autowired
    public AirlineEmployeeService(AirlineEmployeeRepository airlineEmployeeRepository) {
        super(airlineEmployeeRepository);
    }
}
