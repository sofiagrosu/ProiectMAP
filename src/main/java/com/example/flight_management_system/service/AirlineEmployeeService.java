package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.repository.AirlineEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AirlineEmployeeService {
    private final AirlineEmployeeRepository airlineEmployeeRepository;

    @Autowired
    public AirlineEmployeeService(AirlineEmployeeRepository airlineEmployeeRepository){
        this.airlineEmployeeRepository = airlineEmployeeRepository;
    }

    public Iterable<AirlineEmployee> findAll(){
        return airlineEmployeeRepository.findAll();
    }

    public AirlineEmployee findById(String id){
        return airlineEmployeeRepository.findById(id);
    }

    public void save(AirlineEmployee employee){
        airlineEmployeeRepository.save(employee);
    }

    public void delete(AirlineEmployee employee){
        airlineEmployeeRepository.delete(employee);
    }
}
