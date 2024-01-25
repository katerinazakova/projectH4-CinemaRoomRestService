package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.controller;

import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.CinemaSeats;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.exceptionHandling.*;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.service.CinemaService;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.CinemaTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping("/seats")
    public Map<String, Object> viewCinemaInformation() {
        return cinemaService.getCinemaInfo();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseCinemaTicket(@RequestBody CinemaSeats seats) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cinemaService.purchaseTicket(seats.getRow(), seats.getColumn()));
    }

    @PostMapping("/return")
    public ResponseEntity<Object> refundPurchaseCinemaTicket(@RequestBody CinemaTicket cinemaTicket) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cinemaService.refundPurchaseTicket(cinemaTicket.getToken()));
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> viewStatisticsOfCinema(@RequestParam(required = false) String password) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cinemaService.viewStatistics(password));

    }
}
