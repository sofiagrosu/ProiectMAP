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

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Staff findById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
    }

    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);
    }
}
