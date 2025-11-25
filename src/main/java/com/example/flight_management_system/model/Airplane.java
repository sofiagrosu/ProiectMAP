package com.example.flight_management_system.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airplanes")
public class Airplane implements BaseMethods {

    @Id
    private String id;

    private int number;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList<>();

    public Airplane() { this.id = null; this.number = 0; }
    public Airplane(String id, int number) { this.id = id; this.number = number; }

    @Override public String getId() { return id; }
    @Override public void setId(String id) { this.id = id; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
