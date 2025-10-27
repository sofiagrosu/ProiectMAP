package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirlineEmployee;

import java.util.ArrayList;
import java.util.List;

public class AirlineEmployeeRepository implements Repository<AirlineEmployee> {
    private List<AirlineEmployee> airlineEmployeeList;

    public AirlineEmployeeRepository() {
        this.airlineEmployeeList = new ArrayList<>();
    }

    @Override
    public void add(AirlineEmployee item) {
        airlineEmployeeList.add(item);
    }

    @Override
    public void delete(AirlineEmployee item) {
        airlineEmployeeList.remove(item);

    }
    @Override
    public Iterable<AirlineEmployee> findAll() {
        return airlineEmployeeList;
    }
    @Override
    public AirlineEmployee findById(String searchedId) {
        for (AirlineEmployee employee : airlineEmployeeList) {
            if (employee.getId().equals(searchedId)) {
                return employee;
            }
        }
        return null;
    }
}