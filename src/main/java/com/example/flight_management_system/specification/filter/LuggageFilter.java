package com.example.flight_management_system.specification.filter;

import com.example.flight_management_system.model.Status;

public class LuggageFilter {

    private Long ticketId;
    private Status status;

    public LuggageFilter() {}

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
