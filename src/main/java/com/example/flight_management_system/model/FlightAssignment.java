package com.example.flight_management_system.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "flight_assignments")
public class FlightAssignment implements BaseMethods {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    private LocalDate assignmentDate;

    public FlightAssignment() { this.id = null; this.assignmentDate = LocalDate.now(); }
    public FlightAssignment(String id, Flight flight, Staff staff, LocalDate assignmentDate) {
        this.id = id; this.flight = flight; this.staff = staff; this.assignmentDate = assignmentDate;
    }

    @Override public String getId() { return id; }
    @Override public void setId(String id) { this.id = id; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Staff getStaff() { return staff; }
    public void setStaff(Staff staff) { this.staff = staff; }

    public LocalDate getAssignmentDate() { return assignmentDate; }
    public void setAssignmentDate(LocalDate assignmentDate) { this.assignmentDate = assignmentDate; }
}
