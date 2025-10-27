package com.example.flight_management_system.model;

public class FlightAssignment {
    private String id;
    private String flightId;
    private String staffId;
    public FlightAssignment(String id, String flightId, String staffId) {
        this.id = id;
        this.flightId = flightId;
        this.staffId = staffId;
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
