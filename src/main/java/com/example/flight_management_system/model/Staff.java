package com.example.flight_management_system.model;

public abstract class Staff implements BaseMethods {
    private String id;
    private String name;
    public Staff(String id, String name) {
        this.id = id;
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
