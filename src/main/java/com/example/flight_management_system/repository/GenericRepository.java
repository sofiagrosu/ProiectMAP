package com.example.flight_management_system.repository;

public interface GenericRepository<T> {
    public void save(T item);
    public boolean delete(T item);
    public Iterable<T> findAll();
    public T findById(String id);


}
