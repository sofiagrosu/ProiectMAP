package com.example.flight_management_system.repository;


import com.example.flight_management_system.model.BaseMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class InFileRepository < T extends BaseMethods> implements GenericRepository<T> {

    private final File file;
    private final ObjectMapper mapper;  //obiect din biblioteca Jackson, folosit pentru conversia intre obiecte Java-Jason
    private List<T> items;              //lista de obiecte incarcat din fisier
    private Random random = new Random();
    private int newId = random.nextInt(1, 100);

    public InFileRepository(String fileName, Class<T> type) {
        this.mapper = new ObjectMapper() .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
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
    public void saveAll() {
        if (items == null) return;

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, this.items);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(" Error at opening the file: " + file.getName());
        }
    }

    @Override
    public void save(T item) {
        if (item.getId() == null || item.getId().isEmpty()) {
            String newId;
            do {
                newId = generateIdForType(item.getClass());
            } while (findById(newId) != null);
            item.setId(newId);
        }

        items.add(item);
        saveAll();
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
        saveAll();

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

    private String generateIdForType(Class<?> type) {
        String prefix;

        switch (type.getSimpleName().toLowerCase()) {
            case "flight":
                prefix = "fl";
                break;
            case "luggage":
                prefix = "lg";
                break;
            case "noticeboard":
                prefix = "nb";
                break;
            case "airlineemployee":
                prefix = "ae";
                break;
            case "airportemployee":
                prefix = "ape";
                break;
            case "passenger":
                prefix = "ps";
                break;
            case "ticket":
                prefix = "tk";
                break;
            case "flightassignment":
                prefix = "fa";
                break;
            case "airplane":
                prefix = "ap";
                break;
            default:
                prefix = "id";
                break;
        }

        int number = random.nextInt(100, 9999);
        return prefix + "-" + number;
    }

}
