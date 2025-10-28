package com.example.flight_management_system;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.FlightRepository;
import com.example.flight_management_system.service.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlightManagementSystemApplication {

	public static void main(String[] args) {

        SpringApplication.run(FlightManagementSystemApplication.class, args);

	}

    @Bean
    public CommandLineRunner initFlights(FlightService flightService) {
        return args -> {
            Flight flight1 = new Flight(null, "Flight 101", "NB1", "AP1", "G1");
            Flight flight2 = new Flight(null, "Flight 202", "NB2", "AP2", "G2");
            Flight flight3 = new Flight(null, "Flight 303", "NB3", "AP3", "G3");

            flightService.save(flight1);
            flightService.save(flight2);
            flightService.save(flight3);
        };
    }

}
