package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger implements BaseMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Passenger name is required") // Requirement 1.6 a) - Mandatory field
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Currency is required") // Requirement 1.6 a) - Mandatory field
    private String currency;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    // NOTE: Boolean fields like isCheckedIn do not require @NotNull unless they are Objects (Boolean)
    private boolean isCheckedIn;

    public Passenger() { this.name = ""; this.currency = ""; this.isCheckedIn = false; }
    public Passenger(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }
    @Override public Long getId() { return id; }
    @Override public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
    public boolean isCheckedIn() { return isCheckedIn; }
    public void setCheckedIn(boolean checkedIn) { isCheckedIn = checkedIn; }
}