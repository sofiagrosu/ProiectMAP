package com.example.flight_management_system.service;

import com.example.flight_management_system.model.BaseMethods;
import com.example.flight_management_system.repository.GenericRepository;

import java.util.List;

public class CrudService<T extends BaseMethods> {

    protected final GenericRepository<T> repository;

    public CrudService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        T item = repository.findById(id);
        if (item != null) {
            repository.delete(item);
        }
    }

    public void save(T item) {
        repository.save(item);
    }

    public boolean delete(T item) {
        return repository.delete(item);
    }

    public void update(T entity) {
        T existing = repository.findById(entity.getId());
        if (existing != null) {
            repository.delete(existing);
            repository.save(entity);
        }
    }


}
