package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService extends CrudService<Airplane> {

    @Autowired
    public AirplaneService(AirplaneRepository airplaneRepository) {
        super(airplaneRepository);
    }
}
