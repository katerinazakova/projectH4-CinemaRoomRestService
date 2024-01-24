package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.controller;

import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.CinemaSeats;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.ErrorResponse;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling.InvalidSeatCoordinatesException;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling.PurchaseTicketException;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling.UnauthorizedException;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling.WrongTokenException;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.service.CinemaService;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.CinemaTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Map<String, Object> viewCinemaInformation() {
        return cinemaService.getCinemaInfo();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseCinemaTicket(@RequestBody CinemaSeats seats) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cinemaService.purchaseTicket(seats.getRow(), seats.getColumn()));

        } catch (PurchaseTicketException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("The ticket has been already purchased!"));

        } catch (InvalidSeatCoordinatesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid seat coordinates!"));
        }
    }

    @PostMapping("/return")
    public ResponseEntity<Object> refundPurchaseCinemaTicket(@RequestBody CinemaTicket cinemaTicket) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cinemaService.refundPurchaseTicket(cinemaTicket.getToken()));

        } catch (WrongTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Token is wrong!"));
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> viewStatisticsOfCinema(@RequestParam(required = false) String password) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cinemaService.viewStatistics(password));

        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Password is wrong!"));
        }
    }
}
