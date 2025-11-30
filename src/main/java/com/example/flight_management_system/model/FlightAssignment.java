package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "flight_assignments")
public class FlightAssignment implements BaseMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @NotNull(message = "Assignment Date is required")
    private LocalDate assignmentDate;

    public FlightAssignment() { this.assignmentDate = LocalDate.now(); }

    public FlightAssignment(Flight flight, Staff staff, LocalDate assignmentDate) {
        this.flight = flight;
        this.staff = staff;
        this.assignmentDate = assignmentDate;
    }

    @Override public Long getId() { return id; }
    @Override public void setId(Long id) { this.id = id; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Staff getStaff() { return staff; }
    public void setStaff(Staff staff) { this.staff = staff; }

    public LocalDate getAssignmentDate() { return assignmentDate; }
    public void setAssignmentDate(LocalDate assignmentDate) { this.assignmentDate = assignmentDate; }
}