package com.example.flight_management_system.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table (name = "NoticeBoards")
public class NoticeBoard implements BaseMethods{
    @Id
    private String id;
    LocalDate date;
    List<Flight> flightOfTheDay;

    public NoticeBoard( LocalDate date) {
        this.id = UUID.randomUUID().toString();;
        this.date = date;
    }

    public NoticeBoard(){
        this.id = null;
        this.date = LocalDate.now();
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