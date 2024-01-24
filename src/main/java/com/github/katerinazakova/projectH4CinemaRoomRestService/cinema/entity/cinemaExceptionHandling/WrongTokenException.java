package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling;

public class WrongTokenException extends RuntimeException{
    public WrongTokenException(String message) {
        super (message);
    }
}
