package com.example.flight_management_system.model;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public abstract class Staff implements BaseMethods {
    @Id
    private String id;
    private String name;
    public Staff( String name) {
        this.id = UUID.randomUUID().toString();;
        this.name = name;
    }
    public Staff() {
        this.id = null;
        this.name = "";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;   }
}
