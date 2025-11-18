package com.example.flight_management_system.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Flights")
public class Flight implements BaseMethods{
    @Id
    private String id;
    private String name;
    private String gateNumber;

    @ManyToOne
    @JoinColumn(name = "noticeBoardId")
    private NoticeBoard noticeBoard;

    @ManyToOne
    @JoinColumn(name = "airplaneId")
    private Airplane airplane;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightAssignment> flightAssignments = new ArrayList<>();

    public Flight( String name, String noticeBoardId,  String airplaneId, String gateNumber) {
        this.id =  this.id = UUID.randomUUID().toString();
        this.name = name;
//        this.noticeBoardId = noticeBoardId;
//        this.airplaneId = airplaneId;
        this.gateNumber = gateNumber;
    }

    public Flight() {
        this. id = null;
        this.name = "";
//        this.noticeBoardId = "";
//        this.airplaneId = "";
        this.gateNumber = "";
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

//    public String getNoticeBoardId() {
//        return noticeBoardId;
//    }

//    public void setNoticeBoardId(String noticeBoardId) {
//        this.noticeBoardId = noticeBoardId;
//    }

//    public String getAirplaneId() {
//        return airplaneId;
//    }
//
//    public void setAirplaneId(String airplaneId) {
//        this.airplaneId = airplaneId;
//    }

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
