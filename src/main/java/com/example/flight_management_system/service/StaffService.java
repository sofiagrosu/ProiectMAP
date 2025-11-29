package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Staff;
import com.example.flight_management_system.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    /** Returnează toți angajații (Airline + Airport) */
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    /** Caută după ID (funcționează pentru AirlineEmployee și AirportEmployee) */
    public Staff findById(String id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
    }

    /** Creează sau actualizează un angajat */
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    /** Șterge un angajat după ID */
    public void delete(String id) {
        staffRepository.deleteById(id);
    }
}
