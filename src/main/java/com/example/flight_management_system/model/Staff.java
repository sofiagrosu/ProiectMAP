package com.example.flight_management_system.model;

import jakarta.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "staff")
public abstract class Staff implements BaseMethods {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    public Staff() { this.id = null; this.name = ""; }
    public Staff(String id, String name) { this.id = id; this.name = name; }

    @Override public String getId() { return id; }
    @Override public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
