package com.example.flight_management_system.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.flight_management_system.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface FlightRepository extends JpaRepository<Flight, Long> , JpaSpecificationExecutor<Flight> {

    Optional<Flight> findByName(String name);
}