package com.example.flight_management_system.model;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Luggages")
public class Luggage extends BaseMethods{
    private static int counter = 1;

    @Override
    protected String generateCustomId() {
        return "L" + (counter++);
    }

    private Status status;
    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;
    public Luggage( String ticketId, Status status) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
    }

    public Luggage() {
        this.id = null;
        this.status = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getTicketId() {
//        return ticketId;
//    }
//
//    public void setTicketId(String ticketId) {
//        this.ticketId = ticketId;
//    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}