package com.example.flight_management_system.specification.filter;

import com.example.flight_management_system.model.Role;

public class AirlineEmployeeFilter {


    private String name;

    private Role role;

    private String company;

    private String employeeNumber;

    public AirlineEmployeeFilter() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }
}
