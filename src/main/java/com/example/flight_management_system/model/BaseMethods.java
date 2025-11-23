package com.example.flight_management_system.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseMethods {

    @Id
    protected String id;

    @PrePersist
    protected void ensureId() {
        if (id == null || id.isBlank()) {
            id = generateCustomId();
        }
    }

    protected abstract String generateCustomId();

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
