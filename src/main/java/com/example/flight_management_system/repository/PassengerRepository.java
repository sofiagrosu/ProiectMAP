package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PassengerRepository extends InFileRepository<Passenger>  {

    public PassengerRepository() {
        super("passengers.json", Passenger.class);
    }
}
