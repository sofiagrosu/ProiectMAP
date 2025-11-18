package com.example.flight_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericRepository<T>  {
    public void save(T item);
    public boolean delete(T item);
    public List<T> findAll();
    public T findById(String id);
    void update(T item);

}
