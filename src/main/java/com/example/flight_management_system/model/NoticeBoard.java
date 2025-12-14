package com.example.flight_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "noticeboards")
public class NoticeBoard implements BaseMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @OneToMany(mappedBy = "noticeBoard", cascade = CascadeType.ALL)
    private List<Flight> flightsOfTheDay = new ArrayList<>();

    public NoticeBoard() { this.date = LocalDate.now(); }
    public NoticeBoard(LocalDate date) { this.date = date; }
    @Override public Long getId() { return id; }
    @Override public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public List<Flight> getFlightsOfTheDay() { return flightsOfTheDay; }
    public void setFlightsOfTheDay(List<Flight> flightsOfTheDay) { this.flightsOfTheDay = flightsOfTheDay; }


}