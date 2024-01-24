package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling;

public class InvalidSeatCoordinatesException extends RuntimeException{
    public InvalidSeatCoordinatesException(String message) {
        super(message);
    }
}
