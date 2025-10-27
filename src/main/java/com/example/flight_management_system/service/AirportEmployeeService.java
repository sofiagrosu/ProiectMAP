package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.repository.AirportEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportEmployeeService {
    private final AirportEmployeeRepository airportEmployeeRepository;

    @Autowired
    public AirportEmployeeService(AirportEmployeeRepository airportEmployeeRepository) {
        this.airportEmployeeRepository = airportEmployeeRepository;
    }

    public Iterable<AirportEmployee> findAll(){
        return airportEmployeeRepository.findAll();
    }

    public AirportEmployee findById(String id){
        return airportEmployeeRepository.findById(id);
    }

    public void save(AirportEmployee employee){
        airportEmployeeRepository.save(employee);
    }

    public boolean delete(AirportEmployee employee){
        return airportEmployeeRepository.delete(employee);
    }
}
