package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LuggageRepository extends JpaRepository<Luggage, Long> , JpaSpecificationExecutor<Luggage> {
}
