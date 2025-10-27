package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirportEmployee;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AirportEmployeeRepository implements GenericRepository<AirportEmployee> {
    private List<AirportEmployee> airlineEmployeeList;
    public AirportEmployeeRepository(List<AirportEmployee> airlineEmployeeList) {
        this.airlineEmployeeList = airlineEmployeeList;
    }
    @Override
    public void save(AirportEmployee item) {
        airlineEmployeeList.add(item);
    }
    @Override
    public boolean delete(AirportEmployee item) {
        return airlineEmployeeList.remove(item);
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
