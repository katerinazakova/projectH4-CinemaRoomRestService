package com.github.katerinazakova.projectH4CinemaRoomRestService.entity.exceptionHandling;

public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String message) {

        super(message);
    }
}
