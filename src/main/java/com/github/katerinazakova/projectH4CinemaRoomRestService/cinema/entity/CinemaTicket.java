package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaTicket {

        private String token;
        private CinemaSeats ticket;

        public CinemaTicket(CinemaSeats ticket){
            this.ticket = ticket;
        }
        public CinemaTicket(String token, CinemaSeats ticket) {
            this.token = token;
            this.ticket = ticket;

        }
}
