package com.github.katerinazakova.projectH4CinemaRoomRestService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Ticket {
    private String token;
    private Seats ticket;
}
