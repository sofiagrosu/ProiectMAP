package com.example.flight_management_system;

import com.example.flight_management_system.model.*;
import com.example.flight_management_system.repository.*;
import jakarta.persistence.EntityManager;
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
    private final AirlineEmployeeRepository airlineEmployeeRepository;
    private final StaffRepository staffRepository;
    private final AirportEmployeeRepository airportEmployeeRepository;
    private final FlightAssignmentRepository flightAssignmentRepository;
    private final NoticeBoardRepository noticeBoardRepository;

    private final EntityManager entityManager;

    public DataInitializer(AirplaneRepository airplaneRepository,
                           FlightRepository flightRepository,
                           PassengerRepository passengerRepository,
                           TicketRepository ticketRepository,
                           LuggageRepository luggageRepository,
                           AirlineEmployeeRepository airlineEmployeeRepository,
                           StaffRepository staffRepository,
                           AirportEmployeeRepository airportEmployeeRepository,
                           FlightAssignmentRepository flightAssignmentRepository,
                           NoticeBoardRepository noticeBoardRepository,
                           EntityManager entityManager) {
        this.airplaneRepository = airplaneRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.ticketRepository = ticketRepository;
        this.luggageRepository = luggageRepository;
        this.airlineEmployeeRepository = airlineEmployeeRepository;
        this.staffRepository = staffRepository;
        this.airportEmployeeRepository = airportEmployeeRepository;
        this.flightAssignmentRepository = flightAssignmentRepository;
        this.noticeBoardRepository = noticeBoardRepository;
        this.entityManager = entityManager;
    }

    private void resetAutoIncrement(String tableName) {
        // Interogare nativă MySQL
        entityManager.createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1").executeUpdate();
    }

    @Override
    public void run(String... args) throws Exception {

        luggageRepository.deleteAll();
        ticketRepository.deleteAll();
        flightAssignmentRepository.deleteAll();

        airlineEmployeeRepository.deleteAll();
        airportEmployeeRepository.deleteAll();
        staffRepository.deleteAll();

        flightRepository.deleteAll();
        passengerRepository.deleteAll();
        airplaneRepository.deleteAll();
        noticeBoardRepository.deleteAll();


        System.out.println("DataInitializer: Resetting AUTO_INCREMENT counters...");
        resetAutoIncrement("luggage");
        resetAutoIncrement("tickets");
        resetAutoIncrement("flight_assignments");
        resetAutoIncrement("staff"); // Resetarea tabelului părinte este crucială
        resetAutoIncrement("flights");
        resetAutoIncrement("passengers");
        resetAutoIncrement("airplanes");
        resetAutoIncrement("noticeboards");

        System.out.println("DataInitializer: Existing data cleared and AUTO_INCREMENT counters reset. Proceeding with new initialization.");



        List<Airplane> airplanes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Airplane airplane = new Airplane(100 + i);
            airplaneRepository.save(airplane);
            airplanes.add(airplane);
        }


        List<NoticeBoard> boards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NoticeBoard nb = new NoticeBoard(LocalDate.now().plusDays(i));
            noticeBoardRepository.save(nb);
            boards.add(nb);
        }


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


        List<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Passenger p = new Passenger("Passenger " + i, "USD");
            passengerRepository.save(p);
            passengers.add(p);
        }


        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket(
                    passengers.get(i % passengers.size()),
                    flights.get(i % flights.size()),
                    100 + i * 10,
                    "S" + (i + 1)
            );
            ticketRepository.save(ticket);


            for (int j = 1; j <= 2; j++) {
                Luggage l = new Luggage(ticket, Status.CHECKED_IN);
                luggageRepository.save(l);
            }
        }


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

        for (int i = 0; i < 10; i++) {
            FlightAssignment fa = new FlightAssignment(
                    flights.get(i % flights.size()),
                    airlineEmployees.get(i % airlineEmployees.size()),
                    LocalDate.now().plusDays(i)
            );
            flightAssignmentRepository.save(fa);
        }

        System.out.println("DataInitializer: All 10+ entities created successfully!");
    }
}