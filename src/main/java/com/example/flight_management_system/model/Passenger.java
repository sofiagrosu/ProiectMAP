package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger implements BaseMethods {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    private String currency;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    private boolean isCheckedIn;

    public Passenger() { this.id = null; this.name = ""; this.currency = ""; this.isCheckedIn = false; }
    public Passenger(String id, String name, String currency) { this.id = id; this.name = name; this.currency = currency; }

    @Override public String getId() { return id; }
    @Override public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

    public boolean isCheckedIn() { return isCheckedIn; }
    public void setCheckedIn(boolean checkedIn) { isCheckedIn = checkedIn; }
}
