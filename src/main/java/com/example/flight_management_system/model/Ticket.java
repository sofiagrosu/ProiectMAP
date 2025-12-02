package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket implements BaseMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Positive(message = "Price must be positive")
    @Min(value = 1, message = "Price must be at least 1")
    private double price;

    @NotBlank(message = "Seat number is required")
    private String seatNumber;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Luggage> luggages = new ArrayList<>();

    public Ticket() { this.price = 0; this.seatNumber = ""; }

    public Ticket(Passenger passenger, Flight flight, double price, String seatNumber) {
        this.passenger = passenger;
        this.flight = flight;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    @Override public Long getId() { return id; }
    @Override public void setId(Long id) { this.id = id; }

    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public List<Luggage> getLuggages() { return luggages; }
    public void setLuggages(List<Luggage> luggages) { this.luggages = luggages; }
}