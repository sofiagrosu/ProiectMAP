package com.example.flight_management_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "luggage")
public class Luggage implements BaseMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Luggage() { this.status = Status.CHECKED_IN; }

    public Luggage(Ticket ticket, Status status) {
        this.ticket = ticket;
        this.status = status;
    }

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) { this.id = id; }

    public Ticket getTicket() { return ticket; }

    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
