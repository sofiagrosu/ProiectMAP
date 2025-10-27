package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Airplane;

import java.util.ArrayList;

public class AirplaneRepository implements Repository<Airplane>{
    private ArrayList<Airplane> airplanes;
    public AirplaneRepository() {
        this.airplanes = new ArrayList<>();
    }
    @Override
    public void add(Airplane item) {
        airplanes.add(item);
    }
    @Override
    public void delete(Airplane item) {
        airplanes.remove(item);
    }
    @Override
    public Iterable<Airplane> findAll() {
        return airplanes;
    }
    @Override
    public Airplane findById(String searchedId) {
        for (Airplane airplane : airplanes) {
            if (airplane.getId().equals(searchedId)) {
                return airplane;
            }
        }
        return null;
    }
}
