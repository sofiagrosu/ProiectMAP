package com.example.flight_management_system.specification.filter;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class FlightFilter {
    // filtre text (contains)
    private String name;
    private String gateNumber;

    // filtre pe relații (id-uri)
    private Long airplaneId;
    private Long noticeBoardId;

    public FlightFilter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public Long getNoticeBoardId() {
        return noticeBoardId;
    }

    public void setNoticeBoardId(Long noticeBoardId) {
        this.noticeBoardId = noticeBoardId;
    }
}
