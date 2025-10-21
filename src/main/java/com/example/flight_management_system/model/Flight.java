package com.example.flight_management_system.model;

import java.util.List;

public class Flight {
    private String id;
    private String name;
    private String noticeBoardId;
    private String airplaneId;
    private List<Ticket> tickets;
    private List<FlightAssignment> flightAssignments;
}
