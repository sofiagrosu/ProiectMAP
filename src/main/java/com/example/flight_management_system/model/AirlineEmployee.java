package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airline_employee")
public class AirlineEmployee extends Staff {

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "Company name is required")
    private String company;

    @NotBlank(message = "Employee number is required")
    @Column(unique = true)
    private String employeeNumber;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<FlightAssignment> flightAssignments = new ArrayList<>();

    public AirlineEmployee() { super(); this.role = Role.CLOSED; this.company = ""; this.employeeNumber = ""; }

    public AirlineEmployee(String name, Role role, String company, String employeeNumber) {
        super(name);
        this.role = role;
        this.company = company;
        this.employeeNumber = employeeNumber;
    }
    public AirlineEmployee(String name, Role role, String company) {
        super(name);
        this.role = role;
        this.company = company;
        this.employeeNumber = "TEMP-" + System.currentTimeMillis();
    }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public List<FlightAssignment> getFlightAssignments() { return flightAssignments; }
    public void setFlightAssignments(List<FlightAssignment> flightAssignments) { this.flightAssignments = flightAssignments; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }
}