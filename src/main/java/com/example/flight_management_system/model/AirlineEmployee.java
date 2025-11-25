package com.example.flight_management_system.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "airline_employee")
public class AirlineEmployee extends Staff {

    @Enumerated(EnumType.STRING)
    private Role role; // e.g. PILOT, CLOSED

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<FlightAssignment> flightAssignments;

    private String company;

    public AirlineEmployee() { super(); this.role = Role.CLOSED; this.company = ""; }
    public AirlineEmployee(String id, String name, Role role, String company) {
        super(id, name);
        this.role = role;
        this.company = company;
    }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<FlightAssignment> getFlightAssignments() { return flightAssignments; }
    public void setFlightAssignments(List<FlightAssignment> flightAssignments) { this.flightAssignments = flightAssignments; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
}
