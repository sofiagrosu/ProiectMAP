package com.example.flight_management_system;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.repository.FlightRepository;
import com.example.flight_management_system.service.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// Import other necessary model and service classes
import com.example.flight_management_system.model.*;
import com.example.flight_management_system.service.*;

import java.time.LocalDate;

@SpringBootApplication
public class FlightManagementSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(FlightManagementSystemApplication.class, args);

    }


//    @Bean
//    public CommandLineRunner initFlights(FlightService flightService) {
//        return args -> {
//            Flight flight1 = new Flight(null, "Flight 101", "NB1", "AP1", "G1");
//            Flight flight2 = new Flight(null, "Flight 202", "NB2", "AP2", "G2");
//            Flight flight3 = new Flight(null, "Flight 303", "NB3", "AP3", "G3");
//
//            flightService.save(flight1);
//            flightService.save(flight2);
//            flightService.save(flight3);
//        };
//    }
}
//@Bean
//public CommandLineRunner initData(
//        FlightService flightService,
//        AirplaneService airplaneService,
//        NoticeBoardService noticeBoardService,
//        PassengerService passengerService,
//        TicketService ticketService,
//        LuggageService luggageService,
//        FlightAssignmentService flightAssignmentService,
//        AirlineEmployeeService airlineEmployeeService,
//        AirportEmployeeService airportEmployeeService
//) {
//    return args -> {
//
//        // ---------- AIRPLANES ----------
//        Airplane airplane1 = new Airplane(null, 101, null);
//        Airplane airplane2 = new Airplane(null, 202, null);
//        Airplane airplane3 = new Airplane(null, 303, null);
//        airplaneService.save(airplane1);
//        airplaneService.save(airplane2);
//        airplaneService.save(airplane3);
//
//        // ---------- NOTICE BOARDS ----------
//
//        NoticeBoard nb1 = new NoticeBoard(null, LocalDate.of(2025,11,2));
//        NoticeBoard nb2 = new NoticeBoard(null, LocalDate.of(2025,11,3));
//        NoticeBoard nb3 = new NoticeBoard(null, LocalDate.of(2025,12,10));
//        noticeBoardService.save(nb1);
//        noticeBoardService.save(nb2);
//        noticeBoardService.save(nb3);
//        //System.out.println("Notice Boards created with IDs: "+noticeBoardService.findAll() );
//
//        // ---------- FLIGHTS ----------
//        Flight flight1 = new Flight(null, "Flight 101", nb1.getId(), airplane1.getId(), "Gate A");
//        Flight flight2 = new Flight(null, "Flight 202", nb2.getId(), airplane2.getId(), "Gate B");
//        Flight flight3 = new Flight(null, "Flight 303", nb3.getId(), airplane3.getId(), "Gate C");
//      flightService.save(flight1);
//        flightService.save(flight2);
//        flightService.save(flight3);
//
//        // ---------- PASSENGERS ----------
//        Passenger p1 = new Passenger(null, "Alice", "EUR", null);
//        Passenger p2 = new Passenger(null, "Bob", "USD", null);
//        Passenger p3 = new Passenger(null, "Charlie", "GBP", null);
//        passengerService.save(p1);
//        passengerService.save(p2);
//        passengerService.save(p3);
//
//        // ---------- TICKETS ----------
//        Ticket t1 = new Ticket(null, p1.getId(), flight1.getId(), 120.50, "12A");
//        Ticket t2 = new Ticket(null, p2.getId(), flight2.getId(), 99.99, "14C");
//        Ticket t3 = new Ticket(null, p3.getId(), flight3.getId(), 150.00, "16B");
//        ticketService.save(t1);
//        ticketService.save(t2);
//        ticketService.save(t3);
//
//        // ---------- LUGGAGE ----------
//        Luggage l1 = new Luggage(null, t1.getId(), Status.CHECKED_IN);
//        Luggage l2 = new Luggage(null, t2.getId(),Status.LOADED);
//        Luggage l3 = new Luggage(null, t3.getId(), Status.DELIVERED);
//        luggageService.save(l1);
//        luggageService.save(l2);
//        luggageService.save(l3);
//
//        // ---------- EMPLOYEES ----------
//        AirlineEmployee e1 = new AirlineEmployee(null, "John Pilot", Role.PILOT, null, "BlueAir");
//        AirlineEmployee e2 = new AirlineEmployee(null, "Anna Copilot", Role.PILOT, null, "WizzAir");
//        AirlineEmployee e3 = new AirlineEmployee(null, "Marta Crew", Role.PILOT, null, "Ryanair");
//        airlineEmployeeService.save(e1);
//        airlineEmployeeService.save(e2);
//        airlineEmployeeService.save(e3);
//
//
//        AirportEmployee ae1 = new AirportEmployee(null, "Daniel Ops", "Gate Agent", "Operations");
//        AirportEmployee ae2 = new AirportEmployee(null, "Sophie Ground", "Loader", "Ground");
//        AirportEmployee ae3 = new AirportEmployee(null, "Mihai CheckIn", "Desk Agent", "Customer Service");
//        airportEmployeeService.save(ae1);
//        airportEmployeeService.save(ae2);
//        airportEmployeeService.save(ae3);
//
//
//// ---------- FLIGHT ASSIGNMENTS (cu LocalDate assignmentDate) ----------
//        FlightAssignment fa1 = new FlightAssignment(null, flight1.getId(), e1.getId(), LocalDate.now());
//        FlightAssignment fa2 = new FlightAssignment(null, flight2.getId(), e2.getId(), LocalDate.now().plusDays(1));
//        FlightAssignment fa3 = new FlightAssignment(null, flight3.getId(), e3.getId(), LocalDate.now().plusDays(2));
//        flightAssignmentService.save(fa1);
//        flightAssignmentService.save(fa2);
//        flightAssignmentService.save(fa3);
//
//
//
//
//
//        System.out.println("✅ Initial data loaded successfully!");




