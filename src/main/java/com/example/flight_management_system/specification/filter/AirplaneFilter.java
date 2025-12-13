package com.example.flight_management_system.specification.filter;

public class AirplaneFilter {

    // filtrare numerică (range)
    private Integer numberMin;
    private Integer numberMax;

    public AirplaneFilter() {}

    public Integer getNumberMin() {
        return numberMin;
    }

    public void setNumberMin(Integer numberMin) {
        this.numberMin = numberMin;
    }

    public Integer getNumberMax() {
        return numberMax;
    }

    public void setNumberMax(Integer numberMax) {
        this.numberMax = numberMax;
    }
}
