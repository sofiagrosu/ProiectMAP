package com.example.flight_management_system.controller;

import com.example.flight_management_system.controller.AbstractCrudController;
import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/luggage")
public class LuggageController extends AbstractCrudController<Luggage> {

    public LuggageController(CrudService<Luggage> service) {
        super(service,
                "/luggage",
                "luggage/index",
                "luggage/form",
                "luggageList",
                "luggage",
                Luggage::new);
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("luggage") Luggage luggage) {
        service.save(luggage);
        return "redirect:/luggage";
    }
}