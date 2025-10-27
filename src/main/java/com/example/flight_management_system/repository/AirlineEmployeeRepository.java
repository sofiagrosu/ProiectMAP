package com.example.flight_management_system.repository;
import org.springframework.stereotype.Repository;
import com.example.flight_management_system.model.AirlineEmployee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirlineEmployeeRepository implements GenericRepository<AirlineEmployee> {
    private List<AirlineEmployee> airlineEmployeeList;

    public AirlineEmployeeRepository() {
        this.airlineEmployeeList = new ArrayList<>();
    }

    @Override
    public void save(AirlineEmployee item) {
        airlineEmployeeList.add(item);
    }

    @Override
    public boolean delete(AirlineEmployee item) {
        return airlineEmployeeList.remove(item);

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