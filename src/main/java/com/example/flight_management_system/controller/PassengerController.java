package com.example.flight_management_system.controller;

import com.example.flight_management_system.controller.AbstractCrudController;
import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//package com.example.flight_management_system.controller;
//
//import com.example.flight_management_system.model.Luggage;
//import com.example.flight_management_system.model.Passenger;
//import com.example.flight_management_system.model.Ticket;
//import com.example.flight_management_system.service.PassengerService;
//import com.example.flight_management_system.service.LuggageService;
//import com.example.flight_management_system.service.TicketService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/passangers")
//public class PassangerController {
//    private final PassengerService passengerService;
//    private final TicketService ticketService;
//    private final LuggageService luggageService;
//
//    public PassangerController(PassengerService passengerService, TicketService ticketService, LuggageService luggageService) {
//        this.passengerService = passengerService;
//        this.ticketService = ticketService;
//        this.luggageService = luggageService;
//    }
//
//    @GetMapping("/all")
//    @ResponseBody
//    public List<Passenger> passangerAll(){
//        return passengerService.findAll();
//    }
//    @GetMapping("/{id}")
//    @ResponseBody
//    public Passenger passangerId(String id){
//        return passengerService.findById(id);
//    }
//
//    @GetMapping("tickets/all")
//    @ResponseBody
//    public List<Ticket> ticketAll(){
//        return ticketService.findAll();
//    }
//    @GetMapping("/tickets/{id}")
//    @ResponseBody
//    public Ticket ticketId(String id){
//        return ticketService.findById(id);  }
//
//    @GetMapping("/luggages/all")
//    @ResponseBody
//    public List<Luggage> luggageAll(){
//        return luggageService.findAll();
//    }
//    @GetMapping("/luggages/{id}")
//    @ResponseBody
//    public Luggage luggageId(String id) {
//        return luggageService.findById(id);
//    }
//
//}
//

@Controller
@RequestMapping("/passengers")
public class PassengerController extends AbstractCrudController<Passenger> {

    public PassengerController(CrudService<Passenger> service) {
        super(service,
                "/passengers",
                "passenger/index",
                "passenger/form",
                "passengers",
                "passenger",
                Passenger::new);
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("passenger") Passenger passenger) {
        service.save(passenger);
        return "redirect:/passengers";
    }
}
