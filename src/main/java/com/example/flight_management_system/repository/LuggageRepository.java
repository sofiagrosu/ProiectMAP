package com.example.flight_management_system.repository;
import com.example.flight_management_system.model.Luggage;
import java.util.List;
import java.util.ArrayList;


public class LuggageRepository {
    private List<Luggage> luggages = new ArrayList<>();
    private long nextId = 1;

    public Luggage save(Luggage luggage) {
        if (luggage.getId() == null) {
            luggage.setId(String.valueOf(nextId));
            nextId++;
        }
        luggages.add(luggage);
        return luggage;
    }

    public List<Luggage> findAll() {
        return new ArrayList<>(luggages);
    }

    public Luggage findById(String id) {
        for (Luggage luggage : luggages) {
            if (luggage.getId().equals(id)) {
                return luggage;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        Luggage luggage = findById(id);
        if (luggage != null) {
            luggages.remove(luggage);
            return true;
        }
        return false;
    }
}
