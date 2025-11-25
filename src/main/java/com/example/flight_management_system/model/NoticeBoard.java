package com.example.flight_management_system.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "noticeboards")
public class NoticeBoard implements BaseMethods {

    @Id
    private String id;

    private LocalDate date;

    @OneToMany
    @JoinColumn(name = "noticeboard_id")
    private List<Flight> flightsOfTheDay = new ArrayList<>();

    public NoticeBoard() { this.id = null; this.date = LocalDate.now(); }
    public NoticeBoard(String id, LocalDate date) { this.id = id; this.date = date; }

    @Override public String getId() { return id; }
    @Override public void setId(String id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public List<Flight> getFlightsOfTheDay() { return flightsOfTheDay; }
    public void setFlightsOfTheDay(List<Flight> flightsOfTheDay) { this.flightsOfTheDay = flightsOfTheDay; }
}
