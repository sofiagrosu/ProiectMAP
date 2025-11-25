package com.example.flight_management_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "luggage")
public class Luggage implements BaseMethods {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Luggage() { this.id = null; this.status = Status.CHECKED_IN; }
    public Luggage(String id, Ticket ticket, Status status) { this.id = id; this.ticket = ticket; this.status = status; }

    @Override public String getId() { return id; }
    @Override public void setId(String id) { this.id = id; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
