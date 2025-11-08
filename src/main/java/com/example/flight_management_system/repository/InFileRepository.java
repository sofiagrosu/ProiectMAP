package com.example.flight_management_system.repository;


import com.example.flight_management_system.model.BaseMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileRepository < T extends BaseMethods> implements GenericRepository<T> {
//implement method read from jasons and write to jason file in resources folder
private final File file;
    private final ObjectMapper mapper;//(din Jackson) serializează obiecte Java în JSON.
    private List<T> items;

    public InFileRepository(String fileName, Class<T> type) {
        this.mapper = new ObjectMapper();
        this.file = new File("src/main/resources/data/" + fileName);

        // dacă fișierul nu există sau e gol, creează listă goală
        if (file.exists() && file.length() > 0) {
            try {
                CollectionType listType = mapper.getTypeFactory()
                        .constructCollectionType(ArrayList.class, type);
                this.items = mapper.readValue(file, listType);
            } catch (IOException e) {
                e.printStackTrace();
                this.items = new ArrayList<>();
            }
        } else {
            this.items = new ArrayList<>();
        }
    }

    @Override
    public void save(T item) {
        items.add(item);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean delete(T item) {
        boolean removed = items.remove(item);
        if (removed) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, items);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return removed;
    }

    public List<T> findAll() {
        return items;
    }


    @Override
    public T findById(String id) {
        if (id == null || items == null) return null;
        for (T item : items) {
            if (item != null && id.equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

}
