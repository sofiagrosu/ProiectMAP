package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PassengerRepository implements GenericRepository<Passenger> {
    private List<Passenger> passengers =  new ArrayList<>();
    private long nextId = 1;

    @Override
    public void save(Passenger passenger) {
        if (passenger.getId() == null) {
            passenger.setId(String.valueOf(nextId));
            nextId++;
        }
        passengers.add(passenger);

    }
    @Override
    public List<Passenger> findAll() {
        return new ArrayList<>(passengers);
    }
    @Override
    public Passenger findById(String id) {
        for (Passenger passenger : passengers) {
            if (passenger.getId().equals(id)) {
                return passenger;
            }
        }
        return null;
    }
    @Override
    public boolean delete(Passenger passenger) {
        return passengers.remove(passenger);
    }
}
