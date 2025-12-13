package com.example.flight_management_system.service;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.repository.LuggageRepository;
import com.example.flight_management_system.specification.LuggageSpecifications;
import com.example.flight_management_system.specification.filter.LuggageFilter;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class LuggageService {
    private final LuggageRepository repo;
    public LuggageService(LuggageRepository repo){ this.repo = repo; }
    public Page<Luggage> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public Luggage findById(Long id){ return repo.findById(id).orElse(null); }
    public Luggage save(Luggage l){ return repo.save(l); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }
    public Page<Luggage> search (LuggageFilter filter, Pageable pageable){
        return repo.findAll(LuggageSpecifications.withFilter(filter), pageable);
    }


}
