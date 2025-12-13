//package com.example.flight_management_system;
//
//import com.example.flight_management_system.model.*;
//import com.example.flight_management_system.repository.*;
//import jakarta.persistence.EntityManager;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Transactional
//public class DataInitializer implements CommandLineRunner {
//
//    private final AirplaneRepository airplaneRepository;
//    private final FlightRepository flightRepository;
//    private final PassengerRepository passengerRepository;
//    private final TicketRepository ticketRepository;
//    private final LuggageRepository luggageRepository;
//    private final AirlineEmployeeRepository airlineEmployeeRepository;
//    private final StaffRepository staffRepository;
//    private final AirportEmployeeRepository airportEmployeeRepository;
//    private final FlightAssignmentRepository flightAssignmentRepository;
//    private final NoticeBoardRepository noticeBoardRepository;
//
//    private final EntityManager entityManager;
//
//    public DataInitializer(AirplaneRepository airplaneRepository,
//                           FlightRepository flightRepository,
//                           PassengerRepository passengerRepository,
//                           TicketRepository ticketRepository,
//                           LuggageRepository luggageRepository,
//                           AirlineEmployeeRepository airlineEmployeeRepository,
//                           StaffRepository staffRepository,
//                           AirportEmployeeRepository airportEmployeeRepository,
//                           FlightAssignmentRepository flightAssignmentRepository,
//                           NoticeBoardRepository noticeBoardRepository,
//                           EntityManager entityManager) {
//        this.airplaneRepository = airplaneRepository;
//        this.flightRepository = flightRepository;
//        this.passengerRepository = passengerRepository;
//        this.ticketRepository = ticketRepository;
//        this.luggageRepository = luggageRepository;
//        this.airlineEmployeeRepository = airlineEmployeeRepository;
//        this.staffRepository = staffRepository;
//        this.airportEmployeeRepository = airportEmployeeRepository;
//        this.flightAssignmentRepository = flightAssignmentRepository;
//        this.noticeBoardRepository = noticeBoardRepository;
//        this.entityManager = entityManager;
//    }
//
//    private void resetAutoIncrement(String tableName) {
//        entityManager.createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1").executeUpdate();
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // ====== LOGIC UNCHANGED (deleteAll + reset + repository.save) ======
//        luggageRepository.deleteAll();
//        ticketRepository.deleteAll();
//        flightAssignmentRepository.deleteAll();
//
//        airlineEmployeeRepository.deleteAll();
//        airportEmployeeRepository.deleteAll();
//        staffRepository.deleteAll();
//
//        flightRepository.deleteAll();
//        passengerRepository.deleteAll();
//        airplaneRepository.deleteAll();
//        noticeBoardRepository.deleteAll();
//
//        resetAutoIncrement("luggage");
//        resetAutoIncrement("tickets");
//        resetAutoIncrement("flight_assignments");
//        resetAutoIncrement("staff");
//        resetAutoIncrement("flights");
//        resetAutoIncrement("passengers");
//        resetAutoIncrement("airplanes");
//        resetAutoIncrement("noticeboards");
//
//        // ====== ONLY VALUES CHANGED (data more varied) ======
//
//        // Airplanes: same logic, just nicer numbers
//        int[] airplaneNumbers = {180, 220, 189, 156, 250, 174, 200, 140, 260, 210};
//
//        // Flights: keep "Flight X" prefix + keep "Gx" pattern (UI-friendly)
//        String[] routes = {"London", "Frankfurt", "Paris", "Amsterdam", "Vienna", "Rome", "Istanbul", "Madrid", "Dublin", "Zurich"};
//
//        // Passengers: real English names (currency kept USD like before)
//        String[] passengerNames = {
//                "Emily Carter", "James Walker", "Olivia Bennett", "Noah Harrison", "Sophia Mitchell",
//                "Liam Anderson", "Ava Thompson", "Ethan Brooks", "Mia Reynolds", "Daniel Foster"
//        };
//
//        // Seats: keep "Sx" pattern (UI-friendly), but add a suffix to look more realistic
//        // Example: S1-12A, S2-3F ... still starts with S1, S2 etc.
//        String[] seatSuffix = {"12A", "3F", "21C", "7B", "18D", "2A", "14E", "9C", "26F", "5D"};
//
//        // Ticket prices: more varied (still numbers, no logic change)
//        int[] prices = {129, 249, 199, 315, 165, 410, 140, 275, 355, 185};
//
//        // Airline staff: real names (role logic unchanged), company kept as "Company X" (UI-friendly)
//        String[] airlineStaffNames = {
//                "William Turner", "Charlotte Evans", "Henry Collins", "Amelia Morgan", "Benjamin Scott",
//                "Grace Parker", "Samuel Reed", "Ella Cooper", "Jack Murphy", "Chloe Howard"
//        };
//
//        // Airport staff: real names (structure unchanged), dept/designation kept patterned but more human
//        String[] airportStaffNames = {
//                "Michael Reed", "Sophie Young", "Lucas Green", "Isabella Hill", "Thomas King",
//                "Hannah Wright", "David Lewis", "Lily Adams", "Matthew Baker", "Zoe Carter"
//        };
//        String[] departments = {"Security", "Ground Ops", "Maintenance", "Customer Service", "Baggage"};
//
//        // ----- Airplanes -----
//        List<Airplane> airplanes = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            Airplane airplane = new Airplane(airplaneNumbers[i - 1]);
//            airplaneRepository.save(airplane);
//            airplanes.add(airplane);
//        }
//
//        // ----- NoticeBoards (unchanged) -----
//        List<NoticeBoard> boards = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            NoticeBoard nb = new NoticeBoard(LocalDate.now().plusDays(i));
//            noticeBoardRepository.save(nb);
//            boards.add(nb);
//        }
//
//        // ----- Flights (keep Flight X + Gx) -----
//        List<Flight> flights = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Flight flight = new Flight(
//                    "Flight " + (i + 1) + " - " + routes[i],
//                    airplanes.get(i % airplanes.size()),
//                    "G" + (i + 1),
//                    boards.get(i % boards.size())
//            );
//            flightRepository.save(flight);
//            flights.add(flight);
//        }
//
//        // ----- Passengers (real names, currency still USD) -----
//        List<Passenger> passengers = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            Passenger p = new Passenger(passengerNames[i - 1], "USD");
//            passengerRepository.save(p);
//            passengers.add(p);
//        }
//
//        // ----- Tickets + Luggage (same loops, same save logic) -----
//        for (int i = 0; i < 10; i++) {
//            Ticket ticket = new Ticket(
//                    passengers.get(i % passengers.size()),
//                    flights.get(i % flights.size()),
//                    prices[i],
//                    "S" + (i + 1) + "-" + seatSuffix[i]
//            );
//            ticketRepository.save(ticket);
//
//            for (int j = 1; j <= 2; j++) {
//                Luggage l = new Luggage(ticket, Status.CHECKED_IN);
//                luggageRepository.save(l);
//            }
//        }
//
//        // ----- Airline employees (real names, same role logic, company stays "Company X") -----
//        List<AirlineEmployee> airlineEmployees = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            AirlineEmployee ae = new AirlineEmployee(
//                    airlineStaffNames[i - 1],
//                    (i % 2 == 0 ? Role.PILOT : Role.CLOSED),
//                    "Company " + ((i % 3) + 1)
//            );
//            airlineEmployeeRepository.save(ae);
//            airlineEmployees.add(ae);
//        }
//
//        // ----- Airport employees (real names, departments more realistic; employee_number still AEMPxxxx) -----
//        List<AirportEmployee> airportEmployees = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            AirportEmployee ae = new AirportEmployee(
//                    airportStaffNames[i - 1],
//                    departments[(i - 1) % departments.length],
//                    "Designation " + i,
//                    "AEMP" + (2000 + i)
//            );
//            airportEmployeeRepository.save(ae);
//            airportEmployees.add(ae);
//        }
//
//        // ----- Flight assignments (unchanged save logic) -----
//        for (int i = 0; i < 10; i++) {
//            FlightAssignment fa = new FlightAssignment(
//                    flights.get(i % flights.size()),
//                    airlineEmployees.get(i % airlineEmployees.size()),
//                    LocalDate.now().plusDays(i)
//            );
//            flightAssignmentRepository.save(fa);
//        }
//
//        System.out.println("DataInitializer: Entities created successfully!");
//    }
//}
