package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.repository.LuggageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LuggageService {
    private final LuggageRepository repo;
    public LuggageService(LuggageRepository repo){ this.repo = repo; }
    public List<Luggage> findAll(){ return repo.findAll(); }
    public Luggage findById(String id){ return repo.findById(id).orElse(null); }
    public Luggage save(Luggage l){ return repo.save(l); }
    public void delete(String id){ repo.findById(id).ifPresent(repo::delete); }
}
