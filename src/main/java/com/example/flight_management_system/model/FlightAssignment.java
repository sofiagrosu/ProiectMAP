package com.example.flight_management_system.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "FlightAssignments")
public class FlightAssignment extends BaseMethods{
    private static int counter = 1;

    @Override
    protected String generateCustomId() {
        return "FA" + (counter++);
    }

    private String staffId;
    private LocalDate assigmentDate;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private AirlineEmployee employee;


    public FlightAssignment( String flightId, String staffId, LocalDate assigmentDate) {
        this.id = UUID.randomUUID().toString();
//        this.flightId = flightId;
        this.staffId = staffId;
        this.assigmentDate = assigmentDate;
    }
    public FlightAssignment() {
        this.id = null;
//        this.flightId = "";
        this.staffId = "";
        this.assigmentDate = LocalDate.now();
    }

    public LocalDate getAssigmentDate() {
        return assigmentDate;
    }
public void setAssigmentDate(LocalDate assigmentDate) {
        this.assigmentDate = assigmentDate;
}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getFlightId() {
        return flight.getId();
    }
//    public void setFlightId(String flightId) {
//        this.flightId = flightId;
//    }

    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Boolean equals(FlightAssignment other){
        return this.getId().equals(other.getId());
    }
    public String toString(){
        return "FlightAssignment{id="+this.id+", flightId="+this.flight.getId()+", staffId="+this.staffId+"}";
    }

}
