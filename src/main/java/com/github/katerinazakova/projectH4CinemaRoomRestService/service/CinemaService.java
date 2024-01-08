package com.github.katerinazakova.projectH4CinemaRoomRestService.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.Seats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CinemaService {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private List<Seats> seats;

    public CinemaService(List<Seats> seats) {
        this.seats = seats;
    }

    public static List<Seats> createPlanOfCinemaSeats() {
        List<Seats> listOfSeats = new ArrayList<>();
        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLUMNS; j++) {
                Seats seat = new Seats();
                seat.setRow(i);
                seat.setColumn(j);
                listOfSeats.add(seat);
            }
        }
        return listOfSeats;
    }
    @JsonProperty("seatsInfo")
    public Map<String,Object> getCinemaInfo (){
         Map<String,Object> cinemaInfo = new HashMap<>();
                cinemaInfo.put("rows",ROWS);
                cinemaInfo.put("columns",COLUMNS);
                cinemaInfo.put("seats",createPlanOfCinemaSeats());
                return cinemaInfo;
    }
}
