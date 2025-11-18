package com.example.flight_management_system.model;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "Airplanes")
public class Airplane implements BaseMethods {
    @Id
    private String id;
    private int number;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();

    public Airplane( int number, List<Flight> flights) {
        this.id = UUID.randomUUID().toString();;
        this.number = number;
        this.flights = flights;
    }
    public Airplane() {
        this.id = null;
        this.number = 0;
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
    public Boolean equals(Airplane other){
        return this.getId().equals(other.getId());
    }
    public String toString(){
        return "Airplane{id="+this.id+", number="+this.number+", flights="+this.flights+"}";
    }

}
