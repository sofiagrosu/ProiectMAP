package com.example.flight_management_system.repository;
import com.example.flight_management_system.model.Luggage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class LuggageRepository implements GenericRepository<Luggage> {
    private List<Luggage> luggages = new ArrayList<>();
    private long nextId = 1;

    @Override
    public void save(Luggage luggage) {
        if (luggage.getId() == null) {
            luggage.setId(String.valueOf(nextId));
            nextId++;
        }
        luggages.add(luggage);

    }

    @Override
    public List<Luggage> findAll() {
        return new ArrayList<>(luggages);
    }

    @Override
    public Luggage findById(String id) {
        for (Luggage luggage : luggages) {
            if (luggage.getId().equals(id)) {
                return luggage;
            }
        }
        return null;
    }
    @Override
    public boolean delete(Luggage luggage) {
        return luggages.remove(luggage);
    }
}
