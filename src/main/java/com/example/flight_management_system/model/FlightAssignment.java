package com.example.flight_management_system.model;

import java.time.LocalDate;

public class FlightAssignment implements BaseMethods{
    private String id;
    private String flightId;
    private String staffId;
    private LocalDate assigmentDate;
    public FlightAssignment(String id, String flightId, String staffId, LocalDate assigmentDate) {
        this.id = id;
        this.flightId = flightId;
        this.staffId = staffId;
        this.assigmentDate = assigmentDate;
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
        return flightId;
    }
    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

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
        return "FlightAssignment{id="+this.id+", flightId="+this.flightId+", staffId="+this.staffId+"}";
    }

}
