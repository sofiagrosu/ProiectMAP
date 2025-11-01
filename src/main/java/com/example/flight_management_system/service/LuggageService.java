package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.repository.LuggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuggageService extends CrudService<Luggage> {

    @Autowired
    public LuggageService(LuggageRepository luggageRepository) {
        super(luggageRepository);
    }
}
