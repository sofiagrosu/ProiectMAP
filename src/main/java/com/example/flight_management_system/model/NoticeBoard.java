package com.example.flight_management_system.model;

import java.time.LocalDate;
import java.util.List;

public class NoticeBoard implements BaseMethods{
    private String id;
    LocalDate date;
    List<Flight> flightOfTheDay;

    public NoticeBoard(String id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Flight> getFlightOfTheDay() {
        return flightOfTheDay;
    }

    public void setFlightOfTheDay(List<Flight> flightOfTheDay) {
        this.flightOfTheDay = flightOfTheDay;
    }
}