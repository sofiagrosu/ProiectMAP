package com.example.flight_management_system.model;
import java.util.List;

import java.util.List;

public class Airplane {
    private String id;
    private int number;
    List<Flight> flights;
    public Airplane(String id, int number, List<Flight> flights) {
        this.id = id;
        this.number = number;
        this.flights = flights;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public List<Flight> getFlights() {
        return flights;
    }
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

}
