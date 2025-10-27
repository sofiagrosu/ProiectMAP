package com.example.flight_management_system.service;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;

    @Autowired
    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public Iterable<Airplane> findAll(){
        return airplaneRepository.findAll();
    }

    public Airplane findById(String id){
        return airplaneRepository.findById(id);
    }

    public void save(Airplane service){
        airplaneRepository.save(service);
    }

    public boolean delete(Airplane service){
        return airplaneRepository.delete(service);
    }
}
