package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Luggage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.flight_management_system.model.BaseMethods;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryRepo < T extends BaseMethods> implements GenericRepository<T> {
    private List<T> storedList = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void save(T item) {
        if (item.getId() == null) {
            item.setId(String.valueOf(nextId));
            nextId++;
        }
        storedList.add(item);

    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storedList);
    }

    @Override
    public T findById(String id) {
        for (T object : storedList) {
            if (object.getId().equals(id)) {
                return object;
            }
        }
        return null;
    }

    @Override
    public boolean delete(T item) {
        return storedList.remove(item);
    }

    @Override
    public void update(T item) {
        for (int i = 0; i < storedList.size(); i++) {
            if (storedList.get(i).getId().equals(item.getId())) {
                storedList.set(i, item);
                return;
            }
        }

    }
}
