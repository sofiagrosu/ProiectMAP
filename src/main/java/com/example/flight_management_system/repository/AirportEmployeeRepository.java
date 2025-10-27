package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirportEmployee;

import java.util.List;

public class AirportEmployeeRepository implements Repository<AirportEmployee>{
    private List<AirportEmployee> airlineEmployeeList;
    public AirportEmployeeRepository(List<AirportEmployee> airlineEmployeeList) {
        this.airlineEmployeeList = airlineEmployeeList;
    }
    @Override
    public void add(AirportEmployee item) {
        airlineEmployeeList.add(item);
    }
    @Override
    public void delete(AirportEmployee item) {
        airlineEmployeeList.remove(item);
    }
    @Override
    public Iterable<AirportEmployee> findAll() {
       return airlineEmployeeList;
    }
    @Override
    public AirportEmployee findById(String searchedId) {
        for (AirportEmployee employee : airlineEmployeeList) {
            if (employee.getId().equals(searchedId)) {
                return employee;
            }
        }
        return null;
    }

}
