package com.example.flight_management_system.controller;


import com.example.flight_management_system.model.BaseMethods;
import com.example.flight_management_system.repository.GenericRepository;
import com.example.flight_management_system.repository.InMemoryRepo;
import com.example.flight_management_system.service.CrudService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.function.Supplier;

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

    //listeaza toate entitatile
    @GetMapping
    public String list(Model model) {
        model.addAttribute(listModelKey, service.findAll());
        return listView;
    }

    //afiseaza formularul de creare
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute(formModelKey, factory.get());
        return formView;
    }

    //salveaza o entitate noua
    @PostMapping
    public String create(@ModelAttribute(name = "#{T(java.lang.String).valueOf(formModelKey)}") T entity) {
        service.save(entity);
        return "redirect:" + basePath;
    }

    //sterge o entitate
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:" + basePath;
    }

    // afișează formularul de editare pentru o entitate existentă
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        T entity = service.findById(id);
        if (entity == null) {
            // poți redirecționa sau arunca o eroare
            return "redirect:" + basePath;
        }
        model.addAttribute(formModelKey, entity);
        return formView; // poți folosi același view ca la creare
    }

    // salvează modificările unei entități existente
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute(name = "#{T(java.lang.String).valueOf(formModelKey)}") T entity) {
        entity.setId(id); // asigură-te că ID-ul nu se schimbă
        service.update(entity); // apelează metoda de update din CrudService
        return "redirect:" + basePath;
    }
}