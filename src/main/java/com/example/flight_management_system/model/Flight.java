package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight implements BaseMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Flight name is required")
    private String name;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    // FIX: Removed @NotNull to stop validation error when re-loading form
    private Airplane airplane;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightAssignment> flightAssignments = new ArrayList<>();

    @NotBlank(message = "Gate number is required")
    private String gateNumber;

    @ManyToOne
    @JoinColumn(name = "noticeboard_id")
    // FIX: Removed @NotNull to stop validation error when re-loading form
    private NoticeBoard noticeBoard;

    public Flight() { this.name = ""; this.gateNumber = ""; }
    public Flight(String name, Airplane airplane, String gateNumber, NoticeBoard noticeBoard){
        this.name = name;
        this.airplane = airplane;
        this.gateNumber = gateNumber;
        this.noticeBoard = noticeBoard;
    }
    @Override public Long getId() { return id; }
    @Override public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Airplane getAirplane() { return airplane; }
    public void setAirplane(Airplane airplane) { this.airplane = airplane; }
    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
    public List<FlightAssignment> getFlightAssignments() { return flightAssignments; }
    public void setFlightAssignments(List<FlightAssignment> flightAssignments) { this.flightAssignments = flightAssignments; }
    public String getGateNumber() { return gateNumber; }
    public void setGateNumber(String gateNumber) { this.gateNumber = gateNumber; }
    public NoticeBoard getNoticeBoard() { return noticeBoard; }
    public void setNoticeBoard(NoticeBoard noticeBoard) { this.noticeBoard = noticeBoard; }
}