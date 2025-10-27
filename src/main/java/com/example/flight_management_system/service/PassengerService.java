package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger findById(String id) {
        return passengerRepository.findById(id);
    }

    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public List<Passenger> findAll(){
        return passengerRepository.findAll();
    }

    public boolean delete(Passenger passenger) {
        return passengerRepository.delete(passenger);
    }
}
