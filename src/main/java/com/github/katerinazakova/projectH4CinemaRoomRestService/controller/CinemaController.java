package com.github.katerinazakova.projectH4CinemaRoomRestService.controller;

import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.Seats;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.Ticket;
import com.github.katerinazakova.projectH4CinemaRoomRestService.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Map<String, Object> CinemaInformation() {
        return cinemaService.getCinemaInfo();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> buyCinemaTicket(@RequestBody Seats seat) {
        return cinemaService.bookingTicket(seat.getRow(), seat.getColumn());
    }

    @PostMapping ("/return")
    public ResponseEntity<Object> refundTicket (@RequestBody Ticket ticket){
        return cinemaService.refundOfPurchaseTicket(ticket.getToken());
    }

    @GetMapping ("/stats")
    public ResponseEntity<Object> viewStatisticsOfCinema(@RequestParam (required = false) String password){

        return cinemaService.viewStatistics(password);

    }
}
