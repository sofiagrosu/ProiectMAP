package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService extends CrudService<Passenger> {

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        super(passengerRepository);
    }
}
