package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Airplane;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> , JpaSpecificationExecutor<Airplane> {
    Airplane findByNumber(int number);

}
