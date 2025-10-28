package com.example.flight_management_system.model;

import java.util.List;

public class Passenger implements BaseMethods {
    private String id;
    private String name;
    private String currency;
    private List<Ticket> tickets;

    //punctul 5- adaugarea de atribute noi
    private boolean isCheckedIn;

    public Passenger(String id, String name, String currency, List<Ticket> tickets) {
        this.id = id;
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