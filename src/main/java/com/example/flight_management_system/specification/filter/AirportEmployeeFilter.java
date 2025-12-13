package com.example.flight_management_system.specification.filter;

public class AirportEmployeeFilter {

    // Staff(name) - moștenit
    private String name;

    private String department;
    private String designation;
    private String employeeNumber;

    public AirportEmployeeFilter() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }
}
