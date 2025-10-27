package com.example.flight_management_system.repository;

public interface Repository<T> {
    public void add(T item);
    public void delete(T item);
    public Iterable<T> findAll();
    public T findById(String id);

}
