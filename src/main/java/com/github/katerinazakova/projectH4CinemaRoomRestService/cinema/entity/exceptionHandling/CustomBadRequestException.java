package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.exceptionHandling;

public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String message) {

        super(message);
    }
}
