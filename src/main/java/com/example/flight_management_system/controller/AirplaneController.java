package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController extends AbstractCrudController<Airplane> {

    public AirplaneController(CrudService<Airplane> service) {
        super(service,
                "/airplanes",
                "airplanes/index",
                "airplanes/form",
                "airplanes",
                "airplane",
                Airplane::new);
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("airplane") Airplane airplane) {
        service.save(airplane);
        return "redirect:/airplanes";
    }
}