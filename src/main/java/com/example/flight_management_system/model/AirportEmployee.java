package com.example.flight_management_system.model;

public class AirportEmployee extends Staff {
    private String department;
    private String designation;
    public AirportEmployee(String id, String name, String department, String designation) {
        super(id, name);
        this.department = department;
        this.designation = designation;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
