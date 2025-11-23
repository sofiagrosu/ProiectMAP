package com.example.flight_management_system.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;


@Entity
@Table(name = "Passengers")
public class Passenger extends BaseMethods {
    private static int counter = 1;
    @Override
    protected String generateCustomId() {
        return "P" + (counter++);
    }

    private String name;
    private String currency;
  @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();
  @JsonProperty("isCheckedIn")
    private boolean isCheckedIn;

    public Passenger() {
        this.name = "";
        this.currency = "";
        this.tickets = new ArrayList<>();
        this.isCheckedIn = false;
    }

    public Passenger( String name, String currency, List<Ticket> tickets) {
        this.name = name;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    private boolean isCheckedIn(){
        return isCheckedIn;
    }

    public void setCheckedIn(boolean checkedIn){
        isCheckedIn = checkedIn;
    }
}