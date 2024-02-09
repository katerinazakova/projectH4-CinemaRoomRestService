package com.github.katerinazakova.projectH4CinemaRoomRestService.controller;

import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.CinemaSeats;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.CinemaStatistics;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.CinemaTicket;
import com.github.katerinazakova.projectH4CinemaRoomRestService.service.CinemaService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<CinemaTicket> purchaseCinemaTicket(@RequestBody CinemaSeats seats) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cinemaService.purchaseTicket(seats.getRow(), seats.getColumn()));
    }

    @PostMapping("/return")
    public ResponseEntity<Map<String, Object>>refundPurchaseCinemaTicket(@RequestBody CinemaTicket cinemaTicket) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cinemaService.refundPurchaseTicket(cinemaTicket.getToken()));
    }

    @GetMapping("/stats")
    public ResponseEntity<CinemaStatistics> viewStatisticsOfCinema(@RequestParam(required = false) String password) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cinemaService.viewStatistics(password));

    }
}
