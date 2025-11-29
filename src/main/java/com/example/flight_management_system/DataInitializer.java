package com.example.flight_management_system;

import com.example.flight_management_system.model.*;
import com.example.flight_management_system.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final AirplaneRepository airplaneRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;
    private final LuggageRepository luggageRepository;
    private final StaffRepository staffRepository;
    private final AirlineEmployeeRepository airlineEmployeeRepository;
    private final AirportEmployeeRepository airportEmployeeRepository;
    private final FlightAssignmentRepository flightAssignmentRepository;
    private final NoticeBoardRepository noticeBoardRepository;

    public DataInitializer(AirplaneRepository airplaneRepository,
                           FlightRepository flightRepository,
                           PassengerRepository passengerRepository,
                           TicketRepository ticketRepository,
                           LuggageRepository luggageRepository,
                           StaffRepository staffRepository,
                           AirlineEmployeeRepository airlineEmployeeRepository,
                           AirportEmployeeRepository airportEmployeeRepository,
                           FlightAssignmentRepository flightAssignmentRepository,
                           NoticeBoardRepository noticeBoardRepository) {
        this.airplaneRepository = airplaneRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.ticketRepository = ticketRepository;
        this.luggageRepository = luggageRepository;
        this.staffRepository = staffRepository;
        this.airlineEmployeeRepository = airlineEmployeeRepository;
        this.airportEmployeeRepository = airportEmployeeRepository;
        this.flightAssignmentRepository = flightAssignmentRepository;
        this.noticeBoardRepository = noticeBoardRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1️⃣ Airplanes
        List<Airplane> airplanes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Airplane airplane = new Airplane(100 + i);
            airplaneRepository.save(airplane);
            airplanes.add(airplane);
        }

        // 2️⃣ NoticeBoards
        List<NoticeBoard> boards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NoticeBoard nb = new NoticeBoard(LocalDate.now().plusDays(i));
            noticeBoardRepository.save(nb);
            boards.add(nb);
        }

        // 3️⃣ Flights
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Flight flight = new Flight(
                    "Flight " + (i + 1),
                    airplanes.get(i % airplanes.size()),
                    "G" + (i + 1),
                    boards.get(i % boards.size())
            );
            flightRepository.save(flight);
            flights.add(flight);
        }

        // 4️⃣ Passengers
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Passenger p = new Passenger("Passenger " + i, "USD");
            passengerRepository.save(p);
            passengers.add(p);
        }

        // 5️⃣ Tickets & Luggage
        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket(
                    passengers.get(i % passengers.size()),
                    flights.get(i % flights.size()),
                    100 + i * 10,
                    "S" + (i + 1)
            );
            ticketRepository.save(ticket);

            passengers.get(i % passengers.size()).getTickets().add(ticket);
            flights.get(i % flights.size()).getTickets().add(ticket);

            // 2 Luggage per ticket
            for (int j = 1; j <= 2; j++) {
                Luggage l = new Luggage(ticket, Status.CHECKED_IN);
                luggageRepository.save(l);
                ticket.getLuggages().add(l);
            }
        }

        // 6️⃣ Staff: AirlineEmployee
        List<AirlineEmployee> airlineEmployees = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            AirlineEmployee ae = new AirlineEmployee(
                    "Staff " + i,
                    (i % 2 == 0 ? Role.PILOT : Role.CLOSED),
                    "Company " + ((i % 3) + 1)
            );
            airlineEmployeeRepository.save(ae);
            airlineEmployees.add(ae);
        }

        // 7️⃣ Staff: AirportEmployee
        List<AirportEmployee> airportEmployees = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            AirportEmployee ae = new AirportEmployee(
                    "Staff AE " + i,
                    "Dept " + ((i % 3) + 1),
                    "Desig " + i
            );
            airportEmployeeRepository.save(ae);
            airportEmployees.add(ae);
        }

        // 8️⃣ FlightAssignments
        for (int i = 0; i < 10; i++) {
            FlightAssignment fa = new FlightAssignment(
                    flights.get(i % flights.size()),
                    airlineEmployees.get(i % airlineEmployees.size()),
                    LocalDate.now().plusDays(i)
            );
            flightAssignmentRepository.save(fa);

            flights.get(i % flights.size()).getFlightAssignments().add(fa);
            airlineEmployees.get(i % airlineEmployees.size()).getFlightAssignments().add(fa);
        }

        System.out.println("✅ DataInitializer: All entities created successfully!");
    }
}
