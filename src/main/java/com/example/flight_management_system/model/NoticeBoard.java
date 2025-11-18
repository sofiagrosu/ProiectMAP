package com.example.flight_management_system.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table (name = "NoticeBoards")
public class NoticeBoard implements BaseMethods{
    @Id
    private String id;
    LocalDate date;
    @OneToMany(mappedBy = "noticeBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flightsOfTheDay = new ArrayList<>();

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

    public List<Flight> getFlightsOfTheDay() {
        return flightsOfTheDay;
    }

    public void setFlightsOfTheDay(List<Flight> flightOfTheDay) {
        this.flightsOfTheDay = flightOfTheDay;
    }
}