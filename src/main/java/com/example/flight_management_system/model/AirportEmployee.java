package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airport_employee")
public class AirportEmployee extends Staff {

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Designation is required")
    private String designation;

    @NotBlank(message = "Employee number is required")
    @Column(unique = true)
    private String employeeNumber;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<FlightAssignment> flightAssignments = new ArrayList<>();

    public AirportEmployee() {
        super();
        this.employeeNumber = "";
    }

    public AirportEmployee(String name, String department, String designation, String employeeNumber) {
        super(name);
        this.department = department;
        this.designation = designation;
        this.employeeNumber = employeeNumber;
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<FlightAssignment> getFlightAssignments() {
        return flightAssignments;
    }

    public void setFlightAssignments(List<FlightAssignment> flightAssignments) {
        this.flightAssignments = flightAssignments;
    }
}