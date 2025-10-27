package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Airplane;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class AirplaneRepository implements GenericRepository<Airplane>{
    private ArrayList<Airplane> airplanes;
    public AirplaneRepository() {
        this.airplanes = new ArrayList<>();
    }
    @Override
    public void save(Airplane item) {
        airplanes.add(item);
    }
    @Override
    public boolean delete(Airplane item) {
        return airplanes.remove(item);
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
