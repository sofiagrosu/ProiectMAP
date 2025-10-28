package com.example.flight_management_system.model;

import java.util.List;

public class Flight {
    private String id;
    private String name;
    private String noticeBoardId;
    private String airplaneId;
    private List<Ticket> tickets;
    private List<FlightAssignment> flightAssignments;

    //punctul 5- adaugarea de atribute noi
    private String gateNumber;


    public Flight(String id, String name, String noticeBoardId,  String airplaneId, String gateNumber) {
        this.id = id;
        this.name = name;
        this.noticeBoardId = noticeBoardId;
        this.airplaneId = airplaneId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoticeBoardId() {
        return noticeBoardId;
    }

    public void setNoticeBoardId(String noticeBoardId) {
        this.noticeBoardId = noticeBoardId;
    }

    public String getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(String airplaneId) {
        this.airplaneId = airplaneId;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<FlightAssignment> getFlightAssignments() {
        return flightAssignments;
    }

    public void setFlightAssignments(List<FlightAssignment> flightAssignments) {
        this.flightAssignments = flightAssignments;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

}
