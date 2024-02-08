package com.github.katerinazakova.projectH4CinemaRoomRestService.entity.exceptionHandling;

public class CustomUnauthorizedException extends RuntimeException {
    public CustomUnauthorizedException(String message) {
        super(message);
    }

}
