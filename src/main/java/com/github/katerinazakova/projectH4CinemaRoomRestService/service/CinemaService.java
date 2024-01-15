package com.github.katerinazakova.projectH4CinemaRoomRestService.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.ErrorResponse;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.Seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CinemaService {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private final List<Seats> cinemaSeats;

    public CinemaService() {
        this.cinemaSeats = createPlanOfCinemaSeats();
    }

    public List<Seats> createPlanOfCinemaSeats() {
        List<Seats> listOfSeats = new ArrayList<>();
        for (int row = 1; row <= ROWS; row++) {
            for (int column = 1; column <= COLUMNS; column++) {
                Seats seat = new Seats(row, column, row <= 4 ? 10 : 8);
                listOfSeats.add(seat);
            }
        }
        return listOfSeats;
    }

    @JsonProperty("seatsInfo")
    public Map<String, Object> getCinemaInfo() {
        Map<String, Object> cinemaInfo = new HashMap<>();
        cinemaInfo.put("rows", ROWS);
        cinemaInfo.put("columns", COLUMNS);
        cinemaInfo.put("seats", cinemaSeats);
        return cinemaInfo;
    }

    public static boolean isNotValidSizeOfCinema(int row, int column) {
        return row > ROWS || column > COLUMNS || row <= 0 || column <= 0;
    }

    public ResponseEntity<Object> buyTicket(int row, int column) {
        if (isNotValidSizeOfCinema(row, column)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("The number of a row or a column is out of bounds!"));
        }
        Seats requiredSeat = cinemaSeats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst()
                .orElse(null);

        if (requiredSeat != null) {
            if (requiredSeat.isAvailable()) {
                requiredSeat.setAvailable(false);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(requiredSeat);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("The ticket has been already purchased!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid seat coordinates!"));
        }
    }

}
