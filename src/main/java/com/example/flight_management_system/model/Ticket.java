package com.example.flight_management_system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity@Table (name = "Tickets")
public class Ticket extends BaseMethods{
    private static int counter = 1;

    @Override
    protected String generateCustomId() {
        return "T" + (counter++);
    }

    private double price;
    private String seatNumber;
    @ManyToOne
    @JoinColumn(name = "passengerId")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private Flight flight;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Luggage> luggages = new ArrayList<>();

    public Ticket() {

        this.price = 0;
        this.seatNumber = "";
    }

    public Ticket( String passengerId, String flightId, double price, String seatNumber) {
    ;
        this.price = price;
        this.seatNumber = seatNumber;
    }


    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassengerId() {
        return passenger.getId();
    }

//    public void setPassengerId(Passenger passenger) {
//        this.passenger= passengerId;
//    }
//
//    public String getFlightId() {
//        return flightId;
//    }
//
//    public void setFlightId(String flightId) {
//        this.flightId = flightId;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public List<Luggage> getLuggages() {
        return luggages;
    }

    public void setLuggages(List<Luggage> luggages) {
        this.luggages = luggages;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public BaseMethods getFlight() {
     return flight;
    }

    public BaseMethods getPassenger() {
        return passenger;
    }
}