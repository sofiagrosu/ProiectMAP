package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.repository.LuggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuggageService {
    private final LuggageRepository luggageRepository;

    @Autowired
    public LuggageService(LuggageRepository luggageRepository) {
        this.luggageRepository = luggageRepository;
    }

    public List<Luggage> findAll(){
        return luggageRepository.findAll();
    }

    public Luggage findById(String id){
        return luggageRepository.findById(id);
    }

    public boolean deleteById(Luggage luggage){
        return luggageRepository.delete(luggage);
    }

    public void save(Luggage luggage){
        luggageRepository.save(luggage);
    }
}
