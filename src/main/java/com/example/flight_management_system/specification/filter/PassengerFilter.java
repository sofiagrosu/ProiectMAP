package com.example.flight_management_system.specification.filter;

public class PassengerFilter {

    private String name;
    private String currency;
    private Boolean checkedIn; // Boolean, ca să poată fi null (All / true / false)

    public PassengerFilter() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
