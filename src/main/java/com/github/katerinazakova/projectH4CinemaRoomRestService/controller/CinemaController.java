package com.github.katerinazakova.projectH4CinemaRoomRestService.controller;

import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.Seats;
import com.github.katerinazakova.projectH4CinemaRoomRestService.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
        return cinemaService.buyTicket(seat.getRow(), seat.getColumn());
    }
}
