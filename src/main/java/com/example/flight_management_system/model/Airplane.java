package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airplanes")
public class Airplane implements BaseMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Airplane number is required")
    @Min(value = 1, message = "Airplane number must be positive")
    private int number;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList<>();

    public Airplane() { this.number = 0; }
    public Airplane(int number) { this.number = number; }
    @Override public Long getId() { return id; }
    @Override public void setId(Long id) { this.id = id; }
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}