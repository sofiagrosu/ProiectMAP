package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PassengerRepository {
    private List<Passenger> passengers =  new ArrayList<>();
    private long nextId = 1;

    public Passenger save(Passenger passenger) {
        if (passenger.getId() == null) {
            passenger.setId(String.valueOf(nextId));
            nextId++;
        }
        passengers.add(passenger);
        return passenger;
    }

    public List<Passenger> findAll() {
        return new ArrayList<>(passengers);
    }

    public Passenger findById(String id) {
        for (Passenger passenger : passengers) {
            if (passenger.getId().equals(id)) {
                return passenger;
            }
        }
        return null;
    }

    public boolean delete(String id){
        Passenger passenger = findById(id);
        if(passenger != null){
            passengers.remove(passenger);
            return true;
        }
        return false;
    }
}
