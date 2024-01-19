package com.github.katerinazakova.projectH4CinemaRoomRestService.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.ErrorResponse;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.Seats;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.Statistics;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CinemaService {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private final List<Seats> cinemaSeats;

    public final List <Ticket> purchaseTicket;

    public CinemaService(List <Ticket> purchaseTicket) {
        this.cinemaSeats = createPlanOfCinemaSeats();
        this.purchaseTicket = purchaseTicket;
    }

    private List<Seats> createPlanOfCinemaSeats() {
        List<Seats> listOfSeats = new ArrayList<>();
        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLUMNS; j++) {
                Seats seat = new Seats(i, j, i <= 4 ? 10 : 8);
                listOfSeats.add(seat);
            }
        }
        return listOfSeats;
    }

    public Map<String, Object> getCinemaInfo() {
        Map<String, Object> cinemaInfo = new HashMap<>();
        cinemaInfo.put("rows", ROWS);
        cinemaInfo.put("columns", COLUMNS);
        cinemaInfo.put("seats", cinemaSeats);
        return cinemaInfo;
    }

    public ResponseEntity<Object> bookingTicket(int row, int column) {
        if (row > ROWS || column > COLUMNS || row <= 0 || column <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("The number of a row or a column is out of bounds!"));
        }
        Seats requiredSeat = findRequiredSeat(row, column);
        if (requiredSeat != null) {
            if (requiredSeat.isAvailable()) {
                requiredSeat.setAvailable(false);
                Ticket ticket = new Ticket (UUID.randomUUID().toString(),requiredSeat);
                purchaseTicket.add(ticket);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(ticket);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("The ticket has been already purchased!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid seat coordinates!"));
        }
    }

    public Seats findRequiredSeat(int row, int column) {
        return cinemaSeats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst()
                .orElse(null);

    }

    public Ticket findPurchaseTicket (String token) {
        return purchaseTicket.stream ()
                .filter(ticket -> ticket.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }


    public ResponseEntity<Object> refundOfPurchaseTicket (String token) {
        Ticket ticketWithToken = findPurchaseTicket(token);

        if (ticketWithToken != null){
            Seats availabilityOfSeat = findRequiredSeat(ticketWithToken.getTicket().getRow(), ticketWithToken.getTicket().getColumn());
            availabilityOfSeat.setAvailable(true);
            Map<String,Object> refundTicket = new HashMap<>();
            refundTicket.put("ticket",ticketWithToken.getTicket());
            purchaseTicket.remove(ticketWithToken);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(refundTicket);

        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Wrong token!"));
        }

    }

    public ResponseEntity<Object> viewStatistics(String password) {
        if (password != null && password.equals("super_secret")) {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Statistics(purchaseTicket));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("The password is wrong!"));
        }
    }


}
