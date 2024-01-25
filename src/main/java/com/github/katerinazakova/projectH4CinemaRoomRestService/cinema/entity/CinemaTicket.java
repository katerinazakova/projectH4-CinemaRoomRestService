package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CinemaTicket {

        private String token;
        private CinemaSeats ticket;

}
