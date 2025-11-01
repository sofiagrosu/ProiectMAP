package com.example.flight_management_system.controller;


import com.example.flight_management_system.model.BaseMethods;
import com.example.flight_management_system.repository.GenericRepository;
import com.example.flight_management_system.repository.InMemoryRepo;
import com.example.flight_management_system.service.CrudService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.function.Supplier;

/**
 * Controller generic pentru CRUD minimal (list, create, delete) – Tema 2.
 * Subclasele trebuie doar să definească:
 * - path-ul de bază (ex: "/flights")
 * - view-urile (ex: "flights/index", "flights/form")
 * - numele atributelor de model (ex: "flights", "flight")
 * - fabrica de instanțe noi (ex: Flight::new)
 */
public abstract class AbstractCrudController<T extends BaseMethods> {

    protected final CrudService<T> service;
    private final String listView;     // ex: "flights/index"
    private final String formView;     // ex: "flights/form"
    private final String listModelKey; // ex: "flights"
    private final String formModelKey; // ex: "flight"
    private final String basePath;     // ex: "/flights"
    private final Supplier<T> factory; // ex: Flight::new

    protected AbstractCrudController(
            CrudService<T> service,
            String basePath,
            String listView,
            String formView,
            String listModelKey,
            String formModelKey,
            Supplier<T> factory) {
        this.service = service;
        this.basePath = basePath;
        this.listView = listView;
        this.formView = formView;
        this.listModelKey = listModelKey;
        this.formModelKey = formModelKey;
        this.factory = factory;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute(listModelKey, service.findAll());
        return listView;
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute(formModelKey, factory.get());
        return formView;
    }

    @PostMapping
    public String create(@ModelAttribute(name = "#{T(java.lang.String).valueOf(formModelKey)}") T entity) {
        // Notă: dacă expresia de mai sus te încurcă în unele IDE-uri,
        // poți schimba cu @ModelAttribute("<formModelKey>") în subclasă.
        service.save(entity);
        return "redirect:" + basePath;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:" + basePath;
    }
}
