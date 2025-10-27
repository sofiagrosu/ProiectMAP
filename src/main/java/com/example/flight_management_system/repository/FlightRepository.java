package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private List<Flight> flights  = new ArrayList<>();
    private long nextId = 1;

    public Flight save(Flight flight) {
        if (flight.getId() == null) {                   //daca nu are id
            flight.setId(String.valueOf(nextId));       //ii da unul nou
            nextId++;
        }
        flights.add(flight);
        return flight;
    }

    public List<Flight> findAll() {
        return new ArrayList<>(flights);                //returneaza o copie a listei flights
    }

    public Flight findById(String id) {
        for (Flight flight : flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        Flight flight = findById(id);
        if (flight != null) {
            flights.remove(flight);
            return true;
        }
        return false;
    }
}
