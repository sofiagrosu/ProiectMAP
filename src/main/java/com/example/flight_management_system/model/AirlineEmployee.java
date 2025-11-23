package com.example.flight_management_system.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import static com.example.flight_management_system.model.Role.CLOSED;


@Entity
@DiscriminatorValue("AIRLINE")

public class AirlineEmployee extends Staff {
    private static int counter = 1;

    @Override
    protected String generateCustomId() {
        return "ALE" + (counter++);
    }

    private Role role;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightAssignment> assignments = new ArrayList<>();
    private String company;


    public AirlineEmployee(String name, Role role, List<FlightAssignment> flightAssigments, String company) {
        super( name);
        this.role = role;
        this.assignments = flightAssigments;
        this.company = company;
    }
    public AirlineEmployee() {
        super();
        this.role = CLOSED;
        this.assignments = null;
        this.company = "";
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public List<FlightAssignment> getassignments() {
        return assignments;
    }
    public void setassignments(List<FlightAssignment> assignments) {
        this.assignments = assignments;}

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean equals(AirlineEmployee other){
        return this.getId().equals(other.getId());
    }
    public String toString(){
        return "AirlineEmployee{id="+this.getId()+", name="+this.getName()+", role="+this.role+", assignments="+this.assignments+"}";
    }

}
