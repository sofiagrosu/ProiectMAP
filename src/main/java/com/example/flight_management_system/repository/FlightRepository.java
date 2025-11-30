package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByName(String name);
}