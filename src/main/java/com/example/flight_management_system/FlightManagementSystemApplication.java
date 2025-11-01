package com.example.flight_management_system;

import com.example.flight_management_system.model.*;
import com.example.flight_management_system.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootApplication
public class FlightManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            FlightService flightService,
            PassengerService passengerService,
            TicketService ticketService,
            LuggageService luggageService,
            AirlineEmployeeService airlineEmployeeService,
            AirportEmployeeService airportEmployeeService,
            NoticeBoardService noticeBoardService,
            AirplaneService airplaneService,
            FlightAssignmentService flightAssignmentService
    ) {
        return args -> {

            // --- Flights ---
            flightService.save(new Flight(null, "Flight 101", "NB1", "AP1", "G1"));
            flightService.save(new Flight(null, "Flight 202", "NB2", "AP2", "G2"));
            flightService.save(new Flight(null, "Flight 303", "NB3", "AP3", "G3"));
            flightService.save(new Flight(null, "Flight 404", "NB4", "AP4", "G4"));
            flightService.save(new Flight(null, "Flight 505", "NB5", "AP5", "G5"));

            // --- Passengers ---
            passengerService.save(new Passenger(null, "Alice", "USD", Collections.emptyList()));
            passengerService.save(new Passenger(null, "Bob", "EUR", Collections.emptyList()));
            passengerService.save(new Passenger(null, "Charlie", "USD", Collections.emptyList()));
            passengerService.save(new Passenger(null, "Diana", "GBP", Collections.emptyList()));
            passengerService.save(new Passenger(null, "Eve", "USD", Collections.emptyList()));

            // --- Tickets ---
            ticketService.save(new Ticket(null, "P1", "F101", 200.0, "1A"));
            ticketService.save(new Ticket(null, "P2", "F202", 250.0, "2B"));
            ticketService.save(new Ticket(null, "P3", "F303", 300.0, "3C"));
            ticketService.save(new Ticket(null, "P4", "F404", 220.0, "4D"));
            ticketService.save(new Ticket(null, "P5", "F505", 280.0, "5E"));

            // --- Luggages ---
            luggageService.save(new Luggage(null, "T1", "Checked"));
            luggageService.save(new Luggage(null, "T2", "Checked"));
            luggageService.save(new Luggage(null, "T3", "Pending"));
            luggageService.save(new Luggage(null, "T4", "Checked"));
            luggageService.save(new Luggage(null, "T5", "Pending"));

            // --- Airline Employees ---
            airlineEmployeeService.save(new AirlineEmployee(null, "John", "Pilot", Collections.emptyList(), "AirlineA"));
            airlineEmployeeService.save(new AirlineEmployee(null, "Emma", "Co-Pilot", Collections.emptyList(), "AirlineA"));
            airlineEmployeeService.save(new AirlineEmployee(null, "Liam", "Attendant", Collections.emptyList(), "AirlineB"));
            airlineEmployeeService.save(new AirlineEmployee(null, "Olivia", "Attendant", Collections.emptyList(), "AirlineB"));
            airlineEmployeeService.save(new AirlineEmployee(null, "Noah", "Engineer", Collections.emptyList(), "AirlineC"));

            // --- Airport Employees ---
            airportEmployeeService.save(new AirportEmployee(null, "Sophia", "Security", "Officer"));
            airportEmployeeService.save(new AirportEmployee(null, "Mason", "Maintenance", "Technician"));
            airportEmployeeService.save(new AirportEmployee(null, "Isabella", "Check-in", "Agent"));
            airportEmployeeService.save(new AirportEmployee(null, "Ethan", "Security", "Supervisor"));
            airportEmployeeService.save(new AirportEmployee(null, "Ava", "Operations", "Coordinator"));

            // --- Notice Boards ---
            noticeBoardService.save(new NoticeBoard("NB1", LocalDate.now()));
            noticeBoardService.save(new NoticeBoard("NB2", LocalDate.now()));
            noticeBoardService.save(new NoticeBoard("NB3", LocalDate.now()));
            noticeBoardService.save(new NoticeBoard("NB4", LocalDate.now()));
            noticeBoardService.save(new NoticeBoard("NB5", LocalDate.now()));

            // --- Airplanes ---
            airplaneService.save(new Airplane(null, 1001, Collections.emptyList()));
            airplaneService.save(new Airplane(null, 1002, Collections.emptyList()));
            airplaneService.save(new Airplane(null, 1003, Collections.emptyList()));
            airplaneService.save(new Airplane(null, 1004, Collections.emptyList()));
            airplaneService.save(new Airplane(null, 1005, Collections.emptyList()));

            // --- Flight Assignments ---
            flightAssignmentService.save(new FlightAssignment(null, "F101", "AE1", LocalDate.now()));
            flightAssignmentService.save(new FlightAssignment(null, "F202", "AE2", LocalDate.now()));
            flightAssignmentService.save(new FlightAssignment(null, "F303", "AE3", LocalDate.now()));
            flightAssignmentService.save(new FlightAssignment(null, "F404", "AE4", LocalDate.now()));
            flightAssignmentService.save(new FlightAssignment(null, "F505", "AE5", LocalDate.now()));
        };
    }
}
