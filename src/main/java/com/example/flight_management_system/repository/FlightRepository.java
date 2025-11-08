package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository extends InFileRepository<Flight> {

    public FlightRepository() {
        super("flights.json", Flight.class);
    }
}
