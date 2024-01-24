package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {

        super(message);
    }

}
