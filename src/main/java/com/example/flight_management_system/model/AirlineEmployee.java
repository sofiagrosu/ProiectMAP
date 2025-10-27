package com.example.flight_management_system.model;

import java.util.List;

public class AirlineEmployee extends Staff{
    private String role;
    List<FlightAssignment> flightAssigments;
    public AirlineEmployee(String id, String name, String role, List<FlightAssignment> flightAssigments) {
        super(id, name);
        this.role = role;
        this.flightAssigments = flightAssigments;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public List<FlightAssignment> getFlightAssigments() {
        return flightAssigments;
    }
    public void setFlightAssigments(List<FlightAssignment> flightAssigments) {
        this.flightAssigments = flightAssigments;}

    public Boolean equals(AirlineEmployee other){
        return this.getId().equals(other.getId());
    }
    public String toString(){
        return "AirlineEmployee{id="+this.getId()+", name="+this.getName()+", role="+this.role+", flightAssigments="+this.flightAssigments+"}";
    }
}
