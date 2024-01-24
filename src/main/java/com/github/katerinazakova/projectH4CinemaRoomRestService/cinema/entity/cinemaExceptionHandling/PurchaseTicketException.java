package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling;

public class PurchaseTicketException extends RuntimeException{
    public PurchaseTicketException(String message) {
        super(message);
    }
}
