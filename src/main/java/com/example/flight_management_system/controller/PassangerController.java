package com.example.flight_management_system.controller;


import com.example.flight_management_system.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/passangers")
public class PassangerController {
    private final PassangerService passangerService;
    private final TicketService ticketService;
    private final LuggageService luggageService;

    @GetMapping("/all")
    @ResponseBody
    public String passangerAll(){
        return passangerService.findAll();
    }
    @GetMapping("/{id}")
    @ResponseBody
    public String passangerId(int id){
        return passangerService.findById(id);
    }

    @GetMapping("tickets/all")
    @ResponseBody
    public String ticketAll(){
        return ticketService.findAll();
    }
    @GetMapping("/tickets/{id}")
    @ResponseBody
    public String ticketId(int id){
        return ticketService.findById(id);  }

    @GetMapping("/luggages/all")
    @ResponseBody
    public String luggageAll(){
        return luggageService.findAll();
    }
    @GetMapping("/luggages/{id}")
    @ResponseBody
    public String luggageId(int id) {
        return luggageService.findById(id);
    }

    }
}
