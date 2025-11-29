package com.example.flight_management_system.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airport_employee")
public class AirportEmployee extends Staff {

    private String department;
    private String designation;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<FlightAssignment> flightAssignments = new ArrayList<>();

    public AirportEmployee() {
        super();
    }

    public AirportEmployee(String name, String department, String designation) {
        super(name);
        this.department = department;
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<FlightAssignment> getFlightAssignments() {
        return flightAssignments;
    }

    public void setFlightAssignments(List<FlightAssignment> flightAssignments) {
        this.flightAssignments = flightAssignments;
    }
}
