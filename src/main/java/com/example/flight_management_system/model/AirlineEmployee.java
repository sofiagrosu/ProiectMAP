package com.example.flight_management_system.model;

import java.util.List;

public class AirlineEmployee extends Staff {
    private String role;
    private List<FlightAssignment> flightAssigments;
    private String company;
    public AirlineEmployee(String id, String name, String role, List<FlightAssignment> flightAssigments, String company) {
        super(id, name);
        this.role = role;
        this.flightAssigments = flightAssigments;
        this.company = company;
    }
    public AirlineEmployee() {
        super();
        this.role = "";
        this.flightAssigments = null;
        this.company = "";
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

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean equals(AirlineEmployee other){
        return this.getId().equals(other.getId());
    }
    public String toString(){
        return "AirlineEmployee{id="+this.getId()+", name="+this.getName()+", role="+this.role+", flightAssigments="+this.flightAssigments+"}";
    }

}
