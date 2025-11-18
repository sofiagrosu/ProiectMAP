package com.example.flight_management_system.service;

import com.example.flight_management_system.model.BaseMethods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CrudService<T extends BaseMethods> {

    protected final JpaRepository<T, String> repository;

    protected CrudService(JpaRepository<T, String> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }


    public T findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    // la JPA, update = tot save()
    public T update(T entity) {
        return repository.save(entity);
    }
}
