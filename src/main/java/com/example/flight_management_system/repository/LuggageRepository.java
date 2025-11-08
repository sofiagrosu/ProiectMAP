package com.example.flight_management_system.repository;
import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Luggage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class LuggageRepository extends InFileRepository<Luggage>  {
    public LuggageRepository() {
        super("luggages.txt", Luggage.class);
    }
}
