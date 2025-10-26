package com.example.flight_management_system.model;

public class Luggage {
    private String id;
    private String ticketId;
    private String status;

    public Luggage(String id, String ticketId, String status) {
        this.id = id;
        this.ticketId = ticketId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
