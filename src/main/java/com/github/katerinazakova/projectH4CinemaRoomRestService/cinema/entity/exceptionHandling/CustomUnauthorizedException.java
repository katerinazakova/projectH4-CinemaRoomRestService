package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.exceptionHandling;

public class CustomUnauthorizedException extends RuntimeException {
    public CustomUnauthorizedException(String message) {
        super(message);
    }

}
